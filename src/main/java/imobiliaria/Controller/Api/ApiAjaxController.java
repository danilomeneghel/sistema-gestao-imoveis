package imobiliaria.Controller.Api;

import imobiliaria.Service.LocalidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ajax")
@Tag(name = "Ajax Lista")
public class ApiAjaxController {

    @Autowired
    private LocalidadeService localidadeService;

    @GetMapping("/municipioEstado/{idEstado}")
    public ResponseEntity<Map<Long, String>> retornaMunicipios(@PathVariable Long idEstado) {
        return new ResponseEntity<>(localidadeService.findMunicipioAsMapPerEstado(idEstado), HttpStatus.OK);
    }

    @GetMapping("/bairroMunicipio/{idMunicipio}")
    public ResponseEntity<Map<Long, String>> retornaBairros(@PathVariable Long idMunicipio) {
        return new ResponseEntity<>(localidadeService.findBairroAsMapPerMunicipio(idMunicipio), HttpStatus.OK);
    }

}
