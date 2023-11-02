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

    public Imovel findImovelById(Long id) {
        Optional<ImovelEntity> imovelEntity = rep.findById(id);
        if(!imovelEntity.isEmpty()) {
            return modelMapper.map(imovelEntity.get(), Imovel.class);
        }
        return null;
    }

    public Imovel findImovelByCategoria(Categoria categoria) {
        if(categoria.getId() != null) {
            CategoriaEntity categoriaEntity = modelMapper.map(categoria, CategoriaEntity.class);
            ImovelEntity imovelEntity = rep.findByCategoria(categoriaEntity);
            if(imovelEntity != null) {
                return modelMapper.map(imovelEntity, Imovel.class);
            }
        }
        return null;
    }

    public Imovel findImovelByNegocio(Negocio negocio) {
        if(negocio.getId() != null) {
            NegocioEntity negocioEntity = modelMapper.map(negocio, NegocioEntity.class);
            ImovelEntity imovelEntity = rep.findByNegocio(negocioEntity);
            if(imovelEntity != null) {
                return modelMapper.map(imovelEntity, Imovel.class);
            }
        }
        return null;
    }

    public Imovel findImovelByQuarto(Quarto quarto) {
        if(quarto.getId() != null) {
            QuartoEntity quartoEntity = modelMapper.map(quarto, QuartoEntity.class);
            ImovelEntity imovelEntity = rep.findByQuarto(quartoEntity);
            if(imovelEntity != null) {
                return modelMapper.map(imovelEntity, Imovel.class);
            }
        }
        return null;
    }

    public Imovel findImovelByBairro(Bairro bairro) {
        if(bairro.getId() != null) {
            BairroEntity bairroEntity = modelMapper.map(bairro, BairroEntity.class);
            ImovelEntity imovelEntity = rep.findByBairro(bairroEntity);
            if(imovelEntity != null) {
                return modelMapper.map(imovelEntity, Imovel.class);
            }
        }
        return null;
    }

    public Imovel salvarImovel(Imovel imovel) {
        ImovelEntity imo = modelMapper.map(imovel, ImovelEntity.class);
        ImovelEntity salvarImovel = rep.save(imo);
        if(salvarImovel != null) {
            imagemService.armazenarImagem(salvarImovel.getId(), imovel.getFiles());
        }
        return modelMapper.map(salvarImovel, Imovel.class);
    }

    public void excluirImovelById(Long id) {
        rep.deleteById(id);
    }

    public List<Imovel> findImovelByExample(Imovel imovel, Long idMunicipio, Long idEstado,
                                            BigDecimal valorMinimo, BigDecimal valorMaximo) {
        Long idMun = idMunicipio;

        if (idEstado == null) {
            idMun = null;
        }
        if (idMun == null) {
            imovel.getBairro().setId(null);
        }

        List<ImovelEntity> todosImoveis = rep.findAll();

        if (valorMinimo != null) {
            todosImoveis = todosImoveis.stream().filter(imovelMinimo ->
                    imovelMinimo.getValor().compareTo(valorMinimo.subtract(new BigDecimal(0.01))) == 1).collect(Collectors.toList());
        }
        if (valorMaximo != null) {
            todosImoveis = todosImoveis.stream().filter(imovelMaximo ->
                    imovelMaximo.getValor().compareTo(valorMaximo.add(new BigDecimal(0.01))) == -1).collect(Collectors.toList());
        }

        if (imovel.getBairro().getId() == null) {
            if (idMun != null) {
                return todosImoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
            } else if (idEstado != null) {
                return todosImoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
            }
        }

        return todosImoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
    }

}
