package imoveis.controller.api;

import imoveis.model.Imovel;
import imoveis.service.ImovelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/imovel-usuario")
@Tag(name = "Imóvel")
@Validated
public class ApiImovelUsuarioController {

    @Autowired
    private ImovelService imovelService;

    @GetMapping("/todos")
    public ResponseEntity<List<Imovel>> mostrarImoveis() {
        return new ResponseEntity<>(imovelService.findAllImoveis(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imovel> buscarImovelPorId(@PathVariable Long id) {
        return new ResponseEntity<>(imovelService.findImovelById(id), HttpStatus.OK);
    }

}
