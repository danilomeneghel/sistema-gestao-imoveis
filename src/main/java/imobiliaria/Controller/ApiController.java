package imobiliaria.Controller;

import imobiliaria.Entity.Categoria;
import imobiliaria.Entity.Negocio;
import imobiliaria.Entity.Quartos;
import imobiliaria.Service.ClassificadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ClassificadorService serv;
	
	@GetMapping("/categorias")
	public Collection<Categoria> mostrarCategorias() {
		return serv.findAllCategoria();
	}

	@PostMapping("/categoria/cadastro")
	public Categoria cadastroCategoria(@Validated Categoria categoria) {
		return serv.saveCategoria(categoria);
	}

	@PutMapping("/categoria/editar/{id}")
	public Categoria editarCategoria(@PathVariable Long id, @Validated Categoria categoria) {
		Categoria cat = serv.findCategoriaById(id);
		if(cat == null) {
			return new Categoria();
		}
		cat.setNome(categoria.getNome());
		cat.setImoveis(categoria.getImoveis());
		return serv.saveCategoria(cat);
	}

	@DeleteMapping("/categoria/excluir/{id}")
	public void excluirCategoria(@PathVariable Long id) {
		serv.excluirCategoriaById(id);
	}
	
	@GetMapping("/categoria/pesquisa")
    public Collection<Categoria> pesquisar(String pesquisa) {
        return serv.findCategoriaByNome(pesquisa);
    }
	
	@GetMapping("/negocios")
	public Collection<Negocio> mostrarNegocios() {
		return serv.findAllNegocio();
	}

	@PostMapping("/negocio/cadastro")
	public Negocio cadastroNegocio(@Validated Negocio negocio) {
		return serv.saveNegocio(negocio);
	}

	@PutMapping("/negocio/editar/{id}")
	public Negocio editarNegocio(@PathVariable Long id, @Validated Negocio negocio) {
		Negocio neg = serv.findNegocioById(id);
		if(neg == null) {
			return new Negocio();
		}
		neg.setNome(negocio.getNome());
		neg.setImoveis(negocio.getImoveis());
		return serv.saveNegocio(neg);
	}

	@DeleteMapping("/negocio/excluir/{id}")
	public void excluirNegocio(@PathVariable Long id) {
		serv.excluirNegocioById(id);
	}
	
	@GetMapping("/negocio/pesquisa")
    public Collection<Negocio> pesquisarNegocio(String pesquisa) {
        return serv.findNegocioByNome(pesquisa);
    }
	
	@GetMapping("/quartos")
	public Collection<Quartos> mostrarQuartos() {
		return serv.findAllQuartos();
	}

	@PostMapping("/quarto/cadastro")
	public Quartos cadastroQuarto(@Validated Quartos quartos) {
		return serv.saveQuartos(quartos);
	}

	@PutMapping("/quarto/editar/{id}")
	public Quartos editarNegocio(@PathVariable Long id, @Validated Quartos quartos) {
		Quartos qua = serv.findQuartosById(id);
		if(qua == null) {
			return new Quartos();
		}
		qua.setQuantidade(quartos.getQuantidade());
		qua.setDescricao(quartos.getDescricao());
		qua.setImoveis(quartos.getImoveis());
		return serv.saveQuartos(qua);
	}

	@DeleteMapping("/quarto/excluir/{id}")
	public void excluirQuarto(@PathVariable Long id) {
		serv.excluirQuartoById(id);
	}

	@GetMapping("/quarto/pesquisa")
    public Collection<Quartos> pesquisarQuarto(Integer pesquisa) {
        return serv.findQuartoByQuantidade(pesquisa);
    }

}
