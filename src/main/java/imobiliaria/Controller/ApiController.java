package imobiliaria.Controller;

import imobiliaria.Model.*;
import imobiliaria.Service.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@OpenAPIDefinition(info=@Info(title="API Imobiliária"))
@Tag(name = "Gestão de Imóveis")
public class ApiController {

    @Autowired
    private ClassificadorService classficadorService;

    @Autowired
    private ImagemService imagemService;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private LocalidadeService localidadeService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> mostrarCategorias() {
        return new ResponseEntity<>(classficadorService.findAllCategorias(), HttpStatus.OK);
    }

    @PostMapping("/categoria/cadastro")
    public ResponseEntity<Categoria> cadastroCategoria(@Validated Categoria categoria) {
        return new ResponseEntity<>(classficadorService.saveCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/categoria/editar/{id}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Long id, @Validated Categoria categoria) {
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
    public ResponseEntity<Negocio> cadastroNegocio(@Validated Negocio negocio) {
        return new ResponseEntity<>(classficadorService.saveNegocio(negocio), HttpStatus.CREATED);
    }

    @PutMapping("/negocio/editar/{id}")
    public ResponseEntity<Negocio> editarNegocio(@PathVariable Long id, @Validated Negocio negocio) {
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
    public ResponseEntity<Quarto> cadastroQuarto(@Validated Quarto quarto) {
        return new ResponseEntity<>(classficadorService.saveQuarto(quarto), HttpStatus.CREATED);
    }

    @PutMapping("/quarto/editar/{id}")
    public ResponseEntity<Quarto> editarQuarto(@PathVariable Long id, @Validated Quarto quarto) {
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

    @PostMapping("/imagem/cadastro")
    public ResponseEntity<Imagem> cadastroImagem(@Validated Imagem imagem) {
        return new ResponseEntity<>(imagemService.saveImage(imagem), HttpStatus.CREATED);
    }

    @GetMapping("/imoveis")
    public ResponseEntity<List<Imovel>> mostrarImoveis() {
        return new ResponseEntity<>(imovelService.findAllImoveis(), HttpStatus.OK);
    }

    @PostMapping("/imovel/cadastro")
    public ResponseEntity<Imovel> cadastroImovel(@Validated Imovel imovel) {
        return new ResponseEntity<>(imovelService.saveImovel(imovel), HttpStatus.CREATED);
    }

    @PutMapping("/imovel/editar/{id}")
    public ResponseEntity<Imovel> editarImovel(@PathVariable Long id, @Validated Imovel imovel) {
        Imovel imo = imovelService.findImovelById(id);
        if (imo == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        imovel.setId(imo.getId());
        return new ResponseEntity<>(imovelService.saveImovel(imovel), HttpStatus.OK);
    }

    @DeleteMapping("/imovel/excluir/{id}")
    public void excluirImovel(@PathVariable Long id) {
        imovelService.excluirImovelById(id);
    }

    @GetMapping("/imovel/pesquisa")
    public ResponseEntity<List<Imovel>> pesquisarImovel(Imovel imovel, Long idMunicipio, Long idEstado,
                                                        BigDecimal valorMinimo, BigDecimal valorMaximo) {
        return new ResponseEntity<>(imovelService.findImovelByExample(imovel, idMunicipio, idEstado,
                valorMinimo, valorMaximo), HttpStatus.OK);
    }

    @GetMapping("/bairros")
    public ResponseEntity<List<Bairro>> mostrarBairros() {
        return new ResponseEntity<>(localidadeService.findAllBairros(), HttpStatus.OK);
    }

    @PostMapping("/bairro/cadastro")
    public ResponseEntity<Bairro> cadastroBairro(@Validated Bairro bairro) {
        return new ResponseEntity<>(localidadeService.saveBairro(bairro), HttpStatus.CREATED);
    }

    @PutMapping("/bairro/editar/{id}")
    public ResponseEntity<Bairro> editarBairro(@PathVariable Long id, @Validated Bairro bairro) {
        Bairro bai = localidadeService.findBairroById(id);
        if (bai == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        bairro.setId(bai.getId());
        return new ResponseEntity<>(localidadeService.saveBairro(bairro), HttpStatus.OK);
    }

    @DeleteMapping("/bairro/excluir/{id}")
    public void excluirBairro(@PathVariable Long id) {
        localidadeService.excluirBairroById(id);
    }

    @GetMapping("/bairro/pesquisa")
    public ResponseEntity<List<Bairro>> pesquisarBairro(String pesquisa) {
        return new ResponseEntity<>(localidadeService.findBairroByNome(pesquisa), HttpStatus.OK);
    }

    @GetMapping("/estados")
    public ResponseEntity<List<Estado>> mostrarEstados() {
        return new ResponseEntity<>(localidadeService.findAllEstados(), HttpStatus.OK);
    }

    @PostMapping("/estado/cadastro")
    public ResponseEntity<Estado> cadastroEstado(@Validated Estado estado) {
        return new ResponseEntity<>(localidadeService.saveEstado(estado), HttpStatus.CREATED);
    }

    @PutMapping("/estado/editar/{id}")
    public ResponseEntity<Estado> editarEstado(@PathVariable Long id, @Validated Estado estado) {
        Estado est = localidadeService.findEstadoById(id);
        if (est == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        estado.setId(est.getId());
        return new ResponseEntity<>(localidadeService.saveEstado(estado), HttpStatus.OK);
    }

    @DeleteMapping("/estado/excluir/{id}")
    public void excluirEstado(@PathVariable Long id) {
        localidadeService.excluirEstadoById(id);
    }

    @GetMapping("/estado/pesquisa")
    public ResponseEntity<List<Estado>> pesquisarEstado(String pesquisa) {
        return new ResponseEntity<>(localidadeService.findEstadoByNome(pesquisa), HttpStatus.OK);
    }

    @GetMapping("/municipios")
    public ResponseEntity<List<Municipio>> mostrarMunicipios() {
        return new ResponseEntity<>(localidadeService.findAllMunicipios(), HttpStatus.OK);
    }

    @PostMapping("/municipio/cadastro")
    public ResponseEntity<Municipio> cadastroMunicipio(@Validated Municipio municipio) {
        return new ResponseEntity<>(localidadeService.saveMunicipio(municipio), HttpStatus.CREATED);
    }

    @PutMapping("/municipio/editar/{id}")
    public ResponseEntity<Municipio> editarMunicipio(@PathVariable Long id, @Validated Municipio municipio) {
        Municipio mun = localidadeService.findMunicipioById(id);
        if (mun == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        municipio.setId(mun.getId());
        return new ResponseEntity<>(localidadeService.saveMunicipio(municipio), HttpStatus.OK);
    }

    @DeleteMapping("/municipio/excluir/{id}")
    public void excluirMunicipio(@PathVariable Long id) {
        localidadeService.excluirMunicipioById(id);
    }

    @GetMapping("/municipio/pesquisa")
    public ResponseEntity<List<Municipio>> pesquisarMunicipio(String pesquisa) {
        return new ResponseEntity<>(localidadeService.findMunicipioByNome(pesquisa), HttpStatus.OK);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> mostrarUsuarios() {
        return new ResponseEntity<>(myUserDetailsService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/usuario/cadastro")
    public ResponseEntity<User> cadastroUsuario(@Validated User user) {
        return new ResponseEntity<>(myUserDetailsService.saveNewUser(user), HttpStatus.CREATED);
    }

}
