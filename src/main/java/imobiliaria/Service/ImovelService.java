package imobiliaria.Service;

import imobiliaria.Entity.ImovelEntity;
import imobiliaria.Model.Imovel;
import imobiliaria.Repository.ImovelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository rep;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Imovel> findAllImoveis() {
        List<ImovelEntity> imoveis = rep.findAll();
        return imoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
    }

    public Imovel findImovelById(Long id) {
        ImovelEntity imovel = rep.findById(id).get();
        return modelMapper.map(imovel, Imovel.class);
    }

    public Imovel saveImovel(Imovel imovel) {
        ImovelEntity imo = modelMapper.map(imovel, ImovelEntity.class);
        ImovelEntity saveImovel = rep.save(imo);
        return modelMapper.map(saveImovel, Imovel.class);
    }

    public void excluirImovelById(Long id) {
        rep.deleteById(id);
    }

    //Pesquisa detalhada com todos os campos do imóvel, é utilizado um objeto imóvel como exemplo
    //todos os campos nulos são ignorados, podendo ter qualquer valor no banco de dados
    //e os campos com valores devem ser iguais ao do exemplo
    public List<Imovel> findImovelByExample(Imovel imovel, Long idMunicipio, Long idEstado,
                                            BigDecimal valorMinimo, BigDecimal valorMaximo) {
        Long idMun = idMunicipio;
        //Houve casos em que ao desselecionar o Estado ele ainda mantinha o bairro,
        //Assim se o Estado não for enviado o bairro é classificado como nulo a força
        if (idEstado == null) {
            idMun = null;
        }
        if (idMun == null) {
            imovel.getBairro().setId(null);
        }

        List<ImovelEntity> todosImoveis = rep.findAll();

        //Por exemplo apenas é possível encontrar imóveis com valores exatos, porém pesquisas assim
        //são muito limitadas, assim se o form conter um valor máximo ou mínimo é feito um filtro em
        //cima dos dados já pesquisados por exemplo
        if (valorMinimo != null) {
            todosImoveis = todosImoveis.stream().filter(imovelMinimo ->
                    imovelMinimo.getValor().compareTo(valorMinimo.subtract(new BigDecimal(0.01))) == 1).collect(Collectors.toList());
        }
        if (valorMaximo != null) {
            todosImoveis = todosImoveis.stream().filter(imovelMaximo ->
                    imovelMaximo.getValor().compareTo(valorMaximo.add(new BigDecimal(0.01))) == -1).collect(Collectors.toList());
        }

        //A entidade imóvel apenas possui o bairro, sendo possível acessar o Estado e município através dele
        //Desta forma não há como pesquisa-los por exemplo, sendo assim necessário um filtro a parte dos
        //estados e municípios.
        //Se o bairro estiver presente no form ele será pesquisado por exemplo, sendo assim necessário filtros
        //a parte apenas quando ele não estiver preenchido.
        if (imovel.getBairro().getId() == null) {
            //Se o munícipio estiver preenchido não é necessário pesquisar por Estado, afinal este é mais específico
            if (idMun != null) {
                return todosImoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
                //Se ter Estado ele é filtrado, se não significa que nenhuma opção foi selecionada
                //sendo assim necessário apenas retornar a pesquisa original
            } else if (idEstado != null) {
                return todosImoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
            }
        }

        return todosImoveis.stream().map(entity -> modelMapper.map(entity, Imovel.class)).collect(Collectors.toList());
    }

}
