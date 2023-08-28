package imobiliaria.Service;

import imobiliaria.Entity.Imagem;
import imobiliaria.Repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagemService {

	@Autowired
	private ImagemRepository rep;
	
	public void saveImage(Imagem imagem) {
		rep.save(imagem);
	}
	
}
