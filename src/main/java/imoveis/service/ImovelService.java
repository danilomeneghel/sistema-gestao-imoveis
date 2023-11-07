package imoveis.service;

import imoveis.entity.*;
import imoveis.model.*;
import imoveis.repository.ImovelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository rep;

    @Autowired
    private ImagemService imagemService;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Imovel> findAllImoveis() {
        List<ImovelEntity> imoveis = rep.findAll();
        return imoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
    }

    public List<Imovel> findImoveisByAtivo() {
        List<ImovelEntity> imoveis = rep.findByAtivoTrue();
        return imoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
    }

    public Imovel findImovelById(Long id) {
        Optional<ImovelEntity> imovelEntity = rep.findById(id);
        if (!imovelEntity.isEmpty()) {
            return modelMapper.map(imovelEntity.get(), Imovel.class);
        }
        return null;
    }

    public List<Imovel> findImoveisByAtivoAndCategoria(Categoria categoria) {
        if (categoria.getId() != null) {
            CategoriaEntity categoriaEntity = modelMapper.map(categoria, CategoriaEntity.class);
            List<ImovelEntity> imoveis = rep.findByAtivoTrueAndCategoria(categoriaEntity);
            if (!imoveis.isEmpty()) {
                return imoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
            }
        }
        return null;
    }

    public List<Imovel> findImoveisByCategoria(Categoria categoria) {
        if (categoria.getId() != null) {
            CategoriaEntity categoriaEntity = modelMapper.map(categoria, CategoriaEntity.class);
            List<ImovelEntity> imoveis = rep.findByCategoria(categoriaEntity);
            if (!imoveis.isEmpty()) {
                return imoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
            }
        }
        return null;
    }

    public List<Imovel> findImoveisByNegocio(Negocio negocio) {
        if (negocio.getId() != null) {
            NegocioEntity negocioEntity = modelMapper.map(negocio, NegocioEntity.class);
            List<ImovelEntity> imoveis = rep.findByNegocio(negocioEntity);
            if (!imoveis.isEmpty()) {
                return imoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
            }
        }
        return null;
    }

    public List<Imovel> findImoveisByQuarto(Quarto quarto) {
        if (quarto.getId() != null) {
            QuartoEntity quartoEntity = modelMapper.map(quarto, QuartoEntity.class);
            List<ImovelEntity> imoveis = rep.findByQuarto(quartoEntity);
            if (!imoveis.isEmpty()) {
                return imoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
            }
        }
        return null;
    }

    public List<Imovel> findImoveisByBairro(Bairro bairro) {
        if (bairro.getId() != null) {
            BairroEntity bairroEntity = modelMapper.map(bairro, BairroEntity.class);
            List<ImovelEntity> imoveis = rep.findByBairro(bairroEntity);
            if (!imoveis.isEmpty()) {
                return imoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
            }
        }
        return null;
    }

    public Imovel salvarImovel(Imovel imovel) {
        ImovelEntity imo = modelMapper.map(imovel, ImovelEntity.class);
        ImovelEntity salvarImovel = rep.save(imo);
        if (salvarImovel != null) {
            imagemService.armazenarImagem(salvarImovel.getId(), imovel.getFiles());
        }
        return modelMapper.map(salvarImovel, Imovel.class);
    }

    public void excluirImovelById(Long id) {
        rep.deleteById(id);
    }

}
