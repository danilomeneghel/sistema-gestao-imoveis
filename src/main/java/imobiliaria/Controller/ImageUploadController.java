package imobiliaria.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import imobiliaria.Entity.Imagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import imobiliaria.Service.ImagemService;
import imobiliaria.Service.ImovelService;

@Controller
@RequestMapping("/imagem")
public class ImageUploadController {

	@Autowired
	private ImagemService iServ;
	
	@Autowired
	private ImovelService imovelServ;
	
	@PostMapping("/bindImageImovel")
	public ModelAndView vincularImagem(Long id,@RequestParam("file") MultipartFile file) {
		ModelAndView mv = new ModelAndView("redirect:/imovel/visualizar/"+id);		
		Path location;
		if (!file.isEmpty()) {
            try {
                String realPathtoUploads = "C:/Users/clla/eclipse-workspace/Spring/imobiliaria/src/main/resources/static/uploads/";
                //String filePath = realPathtoUploads + iServ.contador();
                String filePath = realPathtoUploads + file.getOriginalFilename();
                String path = "/uploads/"+file.getOriginalFilename();
             
                location = Paths.get(filePath);
                Files.copy(file.getInputStream(), location, 
                        StandardCopyOption.REPLACE_EXISTING);
                
                Imagem imagem = new Imagem(path);
                imagem.setImovel(imovelServ.findImovelById(id));
                iServ.saveImage(imagem);
                
            }catch (Exception e) {
			}
		}
		return mv;
	}
}
