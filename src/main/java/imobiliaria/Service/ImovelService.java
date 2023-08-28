package imobiliaria.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import imobiliaria.Entity.Imovel;
import imobiliaria.Repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

	@Autowired
	private ImovelRepository rep;

	public Collection<Imovel> findAllImoveis(){
		return rep.findAll();
	}
	
	public Imovel findImovelById(Long id){
		return rep.findById(id).get();
	}
	
	public void saveImovel(Imovel imovel) {
		rep.save(imovel);
	}
	
	public void excluirImovelById(Long id) {
		rep.deleteById(id);
	}
	
	//Pesquisa detalhada com todos os campos do imóvel, é utilizado um objeto imóvel como exemplo
	//todos os campos nulos são ignorados, podendo ter qualquer valor no banco de dados
	//e os campos com valores devem ser iguais ao do exemplo
	public Collection<Imovel> findImovelByExample(Imovel imovel, Long idMunicipio, Long idEstado,
			BigDecimal valorMinimo, BigDecimal valorMaximo){
		Long idMun = idMunicipio;
		//Houve casos em que ao desselecionar o estado ele ainda mantinha o bairro,
		//Assim se o estado não for enviado o bairro é classificado como nulo a força
		if(idEstado==null) {
			idMun=null;
		}
		if(idMun==null) {
			imovel.getBairro().setId(null);
		}

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreNullValues();
		Example<Imovel> example = Example.of(imovel,matcher);
		Collection<Imovel> todosImoveis = rep.findAll(example);
		
		//Por exemplo apenas é possível encontrar imóveis com valores exatos, porém pesquisas assim
		//são muito limitadas, assim se o form conter um valor máximo ou mínimo é feito um filtro em
		//cima dos dados já pesquisados por exemplo
		if(valorMinimo!=null) {
			todosImoveis = todosImoveis.stream().filter(imovelMinimo ->
				imovelMinimo.getValor().compareTo(valorMinimo.subtract(new BigDecimal(0.01)))==1).collect(Collectors.toList());
		}
		if(valorMaximo!=null) {
			todosImoveis = todosImoveis.stream().filter(imovelMaximo ->
				imovelMaximo.getValor().compareTo(valorMaximo.add(new BigDecimal(0.01)))==-1).collect(Collectors.toList());
		}
		
		//A entidade imóvel apenas possui o bairro, sendo possível acessar o estado e município através dele
		//Desta forma não há como pesquisa-los por exemplo, sendo assim necessário um filtro a parte dos
		//estados e municípios.
		//Se o bairro estiver presente no form ele será pesquisado por exemplo, sendo assim necessário filtros
		//a parte apenas quando ele não estiver preenchido.
		if(imovel.getBairro().getId()==null) {			
			//Se o munícipio estiver preenchido não é necessário pesquisar por estado, afinal este é mais específico
			if(idMun!=null) {
				return todosImoveis.stream().filter(imovelMunicipio -> 
					imovelMunicipio.getBairro().getMunicipio().getId()==idMunicipio).collect(Collectors.toList());
				//Se ter estado ele é filtrado, se não significa que nenhuma opção foi selecionada
				//sendo assim necessário apenas retornar a pesquisa original
			}else if(idEstado!=null) {
				return todosImoveis.stream().filter(imovelEstado-> 
					imovelEstado.getBairro().getMunicipio().getEstado().getId()==idEstado).collect(Collectors.toList());
			}
		}
		
		return todosImoveis;
	}
	
}
