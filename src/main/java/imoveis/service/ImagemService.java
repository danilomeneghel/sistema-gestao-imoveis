package imoveis.service;

import imoveis.entity.ImagemEntity;
import imoveis.entity.ImovelEntity;
import imoveis.exception.FileStorageException;
import imoveis.model.Imagem;
import imoveis.model.Imovel;
import imoveis.repository.ImagemRepository;
import imoveis.repository.ImovelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImagemService {

    @Value("${file.dir-image}")
    public String DIR_IMAGE;

    @Value("${file.extensions-image}")
    private String EXTENSIONS_IMAGE;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public String armazenarImagem(Long id, MultipartFile arquivo) {
        if (!arquivo.isEmpty()) {
            try {
                Files.createDirectories(Paths.get(DIR_IMAGE));
            } catch (Exception ex) {
                throw new FileStorageException("Não foi possível criar o diretório para armazenar os arquivos.", ex);
            }
            String nomeArquivo = UUID.randomUUID().toString().replace("-", "");
            if (nomeArquivo.contains("..")) {
                throw new FileStorageException("Nome do arquivo contém sequência de caminho inválido " + nomeArquivo);
            }
            String extensaoArquivo = StringUtils.getFilenameExtension(arquivo.getOriginalFilename());
            Path novoNomeArquivo = Paths.get(nomeArquivo + "." + extensaoArquivo);
            if (EXTENSIONS_IMAGE.contains(extensaoArquivo)) {
                this.copyFile(DIR_IMAGE, novoNomeArquivo, arquivo);
            }
            Imagem imagem = new Imagem();
            imagem.setFile(novoNomeArquivo.toString());
            imagem.setPath(DIR_IMAGE);
            Optional<ImovelEntity> imovelEntity = imovelRepository.findById(id);
            Imovel imovel = modelMapper.map(imovelEntity.get(), Imovel.class);
            imagem.setImovel(imovel);
            Imagem imagemSalva = this.saveImage(imagem);
            if (imagemSalva != null) {
                return "Imagem salva com sucesso";
            } else {
                return "Erro ao salvar imagem";
            }
        }
        return "Erro ao salvar imagem";
    }

    public void copyFile(String dir, Path novoNomeArquivo, MultipartFile arquivo) {
        try {
            Path targetLocation = Paths.get(dir).resolve(novoNomeArquivo);
            Files.copy(arquivo.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possivel armazenar o arquivo " +
                    arquivo.getOriginalFilename() + ". Por favor tente novamente", ex);
        }
    }

    public Resource getFile(String nomeArquivo) {
        try {
            Path caminhoArquivo = Paths.get(DIR_IMAGE).resolve(nomeArquivo).normalize();
            Resource resource = new UrlResource(caminhoArquivo.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("Arquivo não encontrado " + nomeArquivo);
            }
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possivel localizar o arquivo " + nomeArquivo +
                    ". Por favor tente novamente", ex);
        }
    }

    public Imagem saveImage(Imagem imagem) {
        ImagemEntity imagemEntity = modelMapper.map(imagem, ImagemEntity.class);
        ImagemEntity saveImagem = imagemRepository.save(imagemEntity);
        return modelMapper.map(saveImagem, Imagem.class);
    }

}
