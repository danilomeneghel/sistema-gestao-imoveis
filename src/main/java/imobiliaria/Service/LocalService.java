package imobiliaria.Service;

import imobiliaria.Entity.BairroEntity;
import imobiliaria.Entity.EstadoEntity;
import imobiliaria.Entity.MunicipioEntity;
import imobiliaria.Model.Estado;
import imobiliaria.Model.Municipio;
import imobiliaria.Repository.BairroRepository;
import imobiliaria.Repository.EstadoRepository;
import imobiliaria.Repository.MunicipioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocalService {

	
	@Autowired
	private EstadoRepository estRep;
	
	@Autowired
	private MunicipioRepository munRep;
	
	@Autowired
	private BairroRepository baiRep;

	private ModelMapper modelMapper = new ModelMapper();

	public List<EstadoEntity> findAllEstados(){
		return estRep.findAll();
	}
	
	public Estado findEstadoById(Long id){
		EstadoEntity estado = estRep.findById(id).get();
		return modelMapper.map(estado, Estado.class);
	}
	
	public List<EstadoEntity> findEstadoByNome(String nome){
		return estRep.findByNomeContainingIgnoreCase(nome);
	}
	
	public void saveEstado(EstadoEntity estado) {
		estRep.save(estado);
	}
	
	public void excluirEstadoById(Long id) {
		estRep.deleteById(id);
	}
	
	public List<MunicipioEntity> findAllMunicipios(){
		return munRep.findAll();
	}
	
	public MunicipioEntity findMunicipioById(Long id){
		return munRep.findById(id).get();
	}
	
	public Municipio saveMunicipio(Municipio municipio) {
		MunicipioEntity mun = modelMapper.map(municipio, MunicipioEntity.class);
		MunicipioEntity saveMunicipio = munRep.save(mun);
		return modelMapper.map(saveMunicipio, Municipio.class);
	}
	
	public void excluirMunicipioById(Long id) {
		munRep.deleteById(id);
	}

	public List<MunicipioEntity> findMunicipioByNome(String nome){
		return munRep.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<BairroEntity> findAllBairros(){
		return baiRep.findAll();
	}
	
	public BairroEntity findBairroById(Long id){
		return baiRep.findById(id).get();
	}
	
	public void saveBairro(BairroEntity bairroEntity) {
		baiRep.save(bairroEntity);
	}
	
	public void excluirBairroById(Long id) {
		baiRep.deleteById(id);
	}

	public List<BairroEntity> findBairroByNome(String nome){
		return baiRep.findByNomeContainingIgnoreCase(nome);
	}
	
	//Para pesquisas ajax utilizar apenas a pesquisa findById retorna
	//uma List com os objetos de tal forma que o json entra em um
	//loop em relações bidirecionais | Estado( municipioEntity( estado( municipioEntity( ...))))|
	//Assim para quebrar o loop retorno um mapa apenas com o Id, para ser capaz de 
	//identificar o objeto, e o nome, para apresentar na tela.
	public Map<Long, String> findMunicipioAsMapPerEstado(Long id){
		Map<Long, String> response = new HashMap<Long,String>();
		List<MunicipioEntity> municipioEntities = findEstadoById(id).getMunicipios();
		for(MunicipioEntity municipioEntity : municipioEntities) {
			response.put(municipioEntity.getId(), municipioEntity.getNome());
		}
		return response;
	}
	
	public Map<Long, String> findBairroAsMapPerMunicipio(Long id){
		Map<Long, String> response = new HashMap<Long,String>();
		List<BairroEntity> bairroEntities = findMunicipioById(id).getBairros();
		for(BairroEntity bairroEntity : bairroEntities) {
			response.put(bairroEntity.getId(), bairroEntity.getNome());
		}
		return response;
	}
}
