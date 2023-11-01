package imoveis.controller;

import imoveis.service.ImagemService;
import imoveis.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/imagem")
public class ImagemController {

    @Autowired
    private ImagemService iServ;

    @Autowired
    private ImovelService imovelServ;

    @GetMapping(path = "/{nomeArquivo:.+}")
    public ResponseEntity<Resource> carregaArquivo(@PathVariable String nomeArquivo, RedirectAttributes ra) {
        Resource resource = iServ.getFile(nomeArquivo);
        String contentType = null;
        try {
            Path path = new File(resource.getFile().getAbsolutePath()).toPath();
            contentType = Files.probeContentType(path);
        } catch (IOException ex) {
            ra.addFlashAttribute("erro", "A Imagem não foi encontrada.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @PostMapping(value = "/armazenar-imagem", consumes = "multipart/form-data")
    public ModelAndView armazenarImagem(Long id, @RequestParam("file") MultipartFile arquivo) {
        iServ.armazenarImagem(id, arquivo);
        ModelAndView mv = new ModelAndView("redirect:/imovel/visualizar/" + id);
        return mv;
    }

}
