package imoveis.controller.api;

import imoveis.model.Imovel;
import imoveis.service.ImovelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/imovel")
@Tag(name = "Imóvel")
@Validated
public class ApiImovelController {

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

    @PostMapping("/imovel/cadastro")
    public ResponseEntity<Imovel> cadastrarImovel(@RequestBody Imovel imovel) {
        return new ResponseEntity<>(imovelService.salvarImovel(imovel), HttpStatus.CREATED);
    }

    @PutMapping("/imovel/editar/{id}")
    public ResponseEntity<Imovel> editarImovel(@PathVariable Long id, @RequestBody Imovel imovel) {
        Imovel imo = imovelService.findImovelById(id);
        if (imo == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        imovel.setId(imo.getId());
        return new ResponseEntity<>(imovelService.salvarImovel(imovel), HttpStatus.OK);
    }

    @DeleteMapping("/imovel/excluir/{id}")
    public void excluirImovel(@PathVariable Long id) {
        imovelService.excluirImovelById(id);
    }

}
