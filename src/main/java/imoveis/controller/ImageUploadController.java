package imoveis.controller;

import imoveis.service.ImagemService;
import imoveis.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/imagem")
public class ImageUploadController {

    @Autowired
    private ImagemService iServ;

    @Autowired
    private ImovelService imovelServ;

    @PostMapping("/armazenarImagem")
    public ModelAndView armazenarImagem(Long id, @RequestParam("file") MultipartFile arquivo) {
        iServ.armazenarImagem(id, arquivo);
        ModelAndView mv = new ModelAndView("redirect:/imovel/visualizar/" + id);
        return mv;
    }

}
