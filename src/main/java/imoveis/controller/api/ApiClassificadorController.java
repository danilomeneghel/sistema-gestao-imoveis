package imoveis.controller.api;

import imoveis.model.Categoria;
import imoveis.model.Negocio;
import imoveis.model.Quarto;
import imoveis.service.ClassificadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classificador")
@Tag(name = "Classificador")
@Validated
public class ApiClassificadorController {

    @Autowired
    private ClassificadorService classficadorService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> mostrarCategorias() {
        return new ResponseEntity<>(classficadorService.findAllCategorias(), HttpStatus.OK);
    }

    @PostMapping("/categoria/cadastro")
    public ResponseEntity<Categoria> cadastroCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(classficadorService.saveCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/categoria/editar/{id}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria cat = classficadorService.findCategoriaById(id);
        if (cat == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        categoria.setId(cat.getId());
        return new ResponseEntity<>(classficadorService.saveCategoria(categoria), HttpStatus.OK);
    }

    @DeleteMapping("/categoria/excluir/{id}")
    public void excluirCategoria(@PathVariable Long id) {
        classficadorService.excluirCategoriaById(id);
    }

    @GetMapping("/categoria/pesquisa")
    public ResponseEntity<List<Categoria>> pesquisarCategoria(String pesquisa) {
        return new ResponseEntity<>(classficadorService.findCategoriaByNome(pesquisa), HttpStatus.OK);
    }

    @GetMapping("/negocios")
    public ResponseEntity<List<Negocio>> mostrarNegocios() {
        return new ResponseEntity<>(classficadorService.findAllNegocios(), HttpStatus.OK);
    }

    @PostMapping("/negocio/cadastro")
    public ResponseEntity<Negocio> cadastroNegocio(@RequestBody Negocio negocio) {
        return new ResponseEntity<>(classficadorService.saveNegocio(negocio), HttpStatus.CREATED);
    }

    @PutMapping("/negocio/editar/{id}")
    public ResponseEntity<Negocio> editarNegocio(@PathVariable Long id, @RequestBody Negocio negocio) {
        Negocio neg = classficadorService.findNegocioById(id);
        if (neg == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        negocio.setId(neg.getId());
        return new ResponseEntity<>(classficadorService.saveNegocio(negocio), HttpStatus.OK);
    }

    @DeleteMapping("/negocio/excluir/{id}")
    public void excluirNegocio(@PathVariable Long id) {
        classficadorService.excluirNegocioById(id);
    }

    @GetMapping("/negocio/pesquisa")
    public ResponseEntity<List<Negocio>> pesquisarNegocio(String pesquisa) {
        return new ResponseEntity<>(classficadorService.findNegocioByNome(pesquisa), HttpStatus.OK);
    }

    @GetMapping("/quartos")
    public ResponseEntity<List<Quarto>> mostrarQuartos() {
        return new ResponseEntity<>(classficadorService.findAllQuartos(), HttpStatus.OK);
    }

    @PostMapping("/quarto/cadastro")
    public ResponseEntity<Quarto> cadastroQuarto(@RequestBody Quarto quarto) {
        return new ResponseEntity<>(classficadorService.saveQuarto(quarto), HttpStatus.CREATED);
    }

    @PutMapping("/quarto/editar/{id}")
    public ResponseEntity<Quarto> editarQuarto(@PathVariable Long id, @RequestBody Quarto quarto) {
        Quarto qua = classficadorService.findQuartoById(id);
        if (qua == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        quarto.setId(qua.getId());
        return new ResponseEntity<>(classficadorService.saveQuarto(quarto), HttpStatus.OK);
    }

    @DeleteMapping("/quarto/excluir/{id}")
    public void excluirQuarto(@PathVariable Long id) {
        classficadorService.excluirQuartoById(id);
    }

    @GetMapping("/quarto/pesquisa")
    public ResponseEntity<List<Quarto>> pesquisarQuarto(Integer pesquisa) {
        return new ResponseEntity<>(classficadorService.findQuartoByQuantidade(pesquisa), HttpStatus.OK);
    }

}
