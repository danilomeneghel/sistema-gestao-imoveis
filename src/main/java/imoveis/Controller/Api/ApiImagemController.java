package imoveis.Controller.Api;

import imoveis.Model.Imagem;
import imoveis.Service.ImagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imagem")
@Tag(name = "Imagem")
@Validated
public class ApiImagemController {

    @Autowired
    private ImagemService imagemService;

    @PostMapping("/imagem/cadastro")
    public ResponseEntity<Imagem> cadastroImagem(@RequestBody Imagem imagem) {
        return new ResponseEntity<>(imagemService.saveImage(imagem), HttpStatus.CREATED);
    }

}
