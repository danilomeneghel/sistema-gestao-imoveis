package imobiliaria.Controller.Api;

import imobiliaria.Service.LocalidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ajax")
@Tag(name = "Ajax Lista")
public class ApiAjaxController {

    @Autowired
    private LocalidadeService localidadeService;

    @GetMapping("/municipioEstado")
    @ResponseBody
    public Map<Long, String> retornaMunicipios(Long idEstado) {
        return localidadeService.findMunicipioAsMapPerEstado(idEstado);
    }

    @GetMapping("/bairroMunicipio")
    @ResponseBody
    public Map<Long, String> retornaBairros(Long idMunicipio) {
        return localidadeService.findBairroAsMapPerMunicipio(idMunicipio);
    }

}
