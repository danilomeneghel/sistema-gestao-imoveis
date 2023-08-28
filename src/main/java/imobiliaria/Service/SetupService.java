package imobiliaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imobiliaria.Repository.BairroRepository;
import imobiliaria.Repository.CategoriaRepository;
import imobiliaria.Repository.EstadoRepository;
import imobiliaria.Repository.ImovelRepository;
import imobiliaria.Repository.MunicipioRepository;
import imobiliaria.Repository.NegocioRepository;
import imobiliaria.Repository.QuartosRepository;

@Service
public class SetupService {
	
	@Autowired
	private BairroRepository bR;
	
	@Autowired
	private CategoriaRepository cR;
	
	@Autowired
	private EstadoRepository eR;

	@Autowired
	private ImovelRepository iR;

	@Autowired
	private MunicipioRepository mR;

	@Autowired
	private NegocioRepository nR;

	@Autowired
	private QuartosRepository qR;
	
	public boolean checkState(){
		if(cR.count()>0 || eR.count()>0 || nR.count()>0)
			return true;
		return false;
	}
	
	public void loadDb() {
		
	}
}
