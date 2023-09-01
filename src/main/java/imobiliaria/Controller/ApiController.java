package imobiliaria.Controller;

import imobiliaria.Model.Categoria;
import imobiliaria.Model.Negocio;
import imobiliaria.Model.Quarto;
import imobiliaria.Service.ClassificadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ClassificadorService serv;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> mostrarCategorias() {
        return new ResponseEntity<>(serv.findAllCategoria(), HttpStatus.OK);
    }

    @PostMapping("/categoria/cadastro")
    public ResponseEntity<Categoria> cadastroCategoria(@Validated Categoria categoria) {
        return new ResponseEntity<>(serv.saveCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/categoria/editar/{id}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Long id, @Validated Categoria categoria) {
        Categoria cat = serv.findCategoriaById(id);
        if (cat == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        cat.setNome(categoria.getNome());
        cat.setImoveis(categoria.getImoveis());
        return new ResponseEntity<>(serv.saveCategoria(cat), HttpStatus.OK);
    }

    @DeleteMapping("/categoria/excluir/{id}")
    public void excluirCategoria(@PathVariable Long id) {
        serv.excluirCategoriaById(id);
    }

    @GetMapping("/categoria/pesquisa")
    public ResponseEntity<List<Categoria>> pesquisarCategoria(String pesquisa) {
        return new ResponseEntity<>(serv.findCategoriaByNome(pesquisa), HttpStatus.OK);
    }

    @GetMapping("/negocios")
    public ResponseEntity<List<Negocio>> mostrarNegocios() {
        return new ResponseEntity<>(serv.findAllNegocio(), HttpStatus.OK);
    }

    @PostMapping("/negocio/cadastro")
    public ResponseEntity<Negocio> cadastroNegocio(@Validated Negocio negocio) {
        return new ResponseEntity<>(serv.saveNegocio(negocio), HttpStatus.CREATED);
    }

    @PutMapping("/negocio/editar/{id}")
    public ResponseEntity<Negocio> editarNegocio(@PathVariable Long id, @Validated Negocio negocio) {
        Negocio neg = serv.findNegocioById(id);
        if (neg == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        neg.setNome(negocio.getNome());
        neg.setImoveis(negocio.getImoveis());
        return new ResponseEntity<>(serv.saveNegocio(neg), HttpStatus.OK);
    }

    @DeleteMapping("/negocio/excluir/{id}")
    public void excluirNegocio(@PathVariable Long id) {
        serv.excluirNegocioById(id);
    }

    @GetMapping("/negocio/pesquisa")
    public ResponseEntity<List<Negocio>> pesquisarNegocio(String pesquisa) {
        return new ResponseEntity<>(serv.findNegocioByNome(pesquisa), HttpStatus.OK);
    }

    @GetMapping("/quartos")
    public ResponseEntity<List<Quarto>> mostrarQuartos() {
        return new ResponseEntity<>(serv.findAllQuarto(), HttpStatus.OK);
    }

    @PostMapping("/quarto/cadastro")
    public ResponseEntity<Quarto> cadastroQuarto(@Validated Quarto quarto) {
        return new ResponseEntity<>(serv.saveQuarto(quarto), HttpStatus.CREATED);
    }

    @PutMapping("/quarto/editar/{id}")
    public ResponseEntity<Quarto> editarQuarto(@PathVariable Long id, @Validated Quarto quarto) {
        Quarto qua = serv.findQuartoById(id);
        if (qua == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        qua.setQuantidade(quarto.getQuantidade());
        qua.setDescricao(quarto.getDescricao());
        qua.setImoveis(quarto.getImoveis());
        return new ResponseEntity<>(serv.saveQuarto(qua), HttpStatus.OK);
    }

    @DeleteMapping("/quarto/excluir/{id}")
    public void excluirQuarto(@PathVariable Long id) {
        serv.excluirQuartoById(id);
    }

    @GetMapping("/quarto/pesquisa")
    public ResponseEntity<List<Quarto>> pesquisarQuarto(Integer pesquisa) {
        return new ResponseEntity<>(serv.findQuartoByQuantidade(pesquisa), HttpStatus.OK);
    }

}
