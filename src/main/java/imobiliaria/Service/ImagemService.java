package imobiliaria.Service;

import imobiliaria.Entity.ImagemEntity;
import imobiliaria.Model.Imagem;
import imobiliaria.Repository.ImagemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository rep;

    private ModelMapper modelMapper = new ModelMapper();

    public Imagem saveImage(Imagem imagem) {
        ImagemEntity imagemEntity = modelMapper.map(imagem, ImagemEntity.class);
        ImagemEntity saveImagem = rep.save(imagemEntity);
        return modelMapper.map(saveImagem, Imagem.class);
    }

}
