package imobiliaria.Controller;

import imobiliaria.Model.Categoria;
import imobiliaria.Model.Negocio;
import imobiliaria.Model.Quarto;
import imobiliaria.Service.ClassificadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/classificador")
public class ClassificadorController {

    @Autowired
    private ClassificadorService serv;

    @GetMapping("/categorias")
    public ModelAndView mostrarCategorias() {
        ModelAndView mv = new ModelAndView("categoria/categorias");
        mv.addObject("categorias", serv.findAllCategorias());
        return mv;
    }

    @GetMapping("/categoria/cadastro")
    public ModelAndView cadastroCategoria() {
        ModelAndView mv = new ModelAndView("categoria/categoriaCadastro");
        mv.addObject("categoria", new Categoria());
        return mv;
    }

    @PostMapping("/categoria/cadastro-preenchido")
    public ModelAndView cadastrarCategoria(@Validated Categoria categoria, Errors errors) {
        ModelAndView mv = new ModelAndView("categoria/categoriaCadastro");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "A Categoria foi cadastrada com sucesso!");
        serv.saveCategoria(categoria);
        mv.addObject("categoria", new Categoria());
        return mv;
    }

    @GetMapping("/categoria/editar/{id}")
    public ModelAndView editarCategoria(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("categoria/categoriaEditar");
        mv.addObject("categoria", serv.findCategoriaById(id));
        return mv;
    }

    @PostMapping("/categoria/editar")
    public ModelAndView editandoCategoria(@Validated Categoria categoria, Errors errors) {
        ModelAndView mv = new ModelAndView("categoria/categoriaEditar");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "A Categoria foi atualizada com sucesso!");
        categoria.setImoveis(serv.findCategoriaById(categoria.getId()).getImoveis());
        serv.saveCategoria(categoria);
        return mv;
    }

    @GetMapping("/categoria/excluir/{id}")
    public ModelAndView excluirCategoria(@PathVariable Long id, RedirectAttributes ra) {
        if (serv.findCategoriaById(id).getImoveis().size() > 0) {
            ra.addFlashAttribute("customMessage", "Não é possível excluir uma categoria com imóveis vinculados.");
            return new ModelAndView("redirect:/classificador/categorias");
        }
        serv.excluirCategoriaById(id);
        ra.addFlashAttribute("sucesso", "A Categoria foi excluída com sucesso.");
        return new ModelAndView("redirect:/classificador/categorias");
    }

    @GetMapping("/categoria/pesquisa")
    public ModelAndView pesquisar(String pesquisa) {
        ModelAndView mv = new ModelAndView("categoria/categorias");
        mv.addObject("categorias", serv.findCategoriaByNome(pesquisa));
        if (pesquisa.isBlank()) {
            return mv;
        }
        mv.addObject("texto_busca", " contendo " + pesquisa);
        return mv;
    }

    @GetMapping("/negocios")
    public ModelAndView mostrarNegocios() {
        ModelAndView mv = new ModelAndView("negocio/negocios");
        mv.addObject("negocios", serv.findAllNegocios());
        return mv;
    }

    @GetMapping("/negocio/cadastro")
    public ModelAndView cadastroNegocio() {
        ModelAndView mv = new ModelAndView("negocio/negocioCadastro");
        mv.addObject("negocio", new Negocio());
        return mv;
    }

    @PostMapping("/negocio/cadastro-preenchido")
    public ModelAndView cadastrarNegocio(@Validated Negocio negocio, Errors errors) {
        ModelAndView mv = new ModelAndView("negocio/negocioCadastro");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "O Negócio foi cadastrado com sucesso!");
        serv.saveNegocio(negocio);
        mv.addObject("negocio", new Negocio());
        return mv;
    }

    @GetMapping("/negocio/editar/{id}")
    public ModelAndView editarNegocio(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("negocio/negocioEditar");
        mv.addObject("negocio", serv.findNegocioById(id));
        return mv;
    }

    @PostMapping("/negocio/editar")
    public ModelAndView editandoNegocio(@Validated Negocio negocio, Errors errors) {
        ModelAndView mv = new ModelAndView("negocio/negocioEditar");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "O Negócio foi atualizado com sucesso!");
        negocio.setImoveis(serv.findNegocioById(negocio.getId()).getImoveis());
        serv.saveNegocio(negocio);
        return mv;
    }

    @GetMapping("/negocio/excluir/{id}")
    public ModelAndView excluirNegocio(@PathVariable Long id, RedirectAttributes ra) {
        if (serv.findNegocioById(id).getImoveis().size() > 0) {
            ra.addFlashAttribute("customMessage", "Não é possível excluir um negócio com imóveis vinculados.");
            return new ModelAndView("redirect:/classificador/negocios");
        }
        serv.excluirNegocioById(id);
        ra.addFlashAttribute("sucesso", "O Negócio foi excluído com sucesso.");
        return new ModelAndView("redirect:/classificador/negocios");
    }

    @GetMapping("/negocio/pesquisa")
    public ModelAndView pesquisarNegocio(String pesquisa) {
        ModelAndView mv = new ModelAndView("negocio/negocios");
        mv.addObject("negocios", serv.findNegocioByNome(pesquisa));
        if (pesquisa.isBlank()) {
            return mv;
        }
        mv.addObject("texto_busca", " contendo " + pesquisa);
        return mv;
    }

    @GetMapping("/quartos")
    public ModelAndView mostrarQuartos() {
        ModelAndView mv = new ModelAndView("quarto/quartos");
        mv.addObject("quartos", serv.findAllQuartos());
        return mv;
    }

    @GetMapping("/quarto/cadastro")
    public ModelAndView cadastroQuarto() {
        ModelAndView mv = new ModelAndView("quarto/quartoCadastro");
        mv.addObject("quarto", new Quarto());
        return mv;
    }

    @PostMapping("/quarto/cadastro-preenchido")
    public ModelAndView cadastrarQuarto(@Validated Quarto quarto, Errors errors) {
        ModelAndView mv = new ModelAndView("quarto/quartoCadastro");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "O Quarto foi cadastrado com sucesso!");
        serv.saveQuarto(quarto);
        mv.addObject("quarto", new Quarto());
        return mv;
    }

    @GetMapping("/quarto/editar/{id}")
    public ModelAndView editarQuarto(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("quarto/quartoEditar");
        mv.addObject("quarto", serv.findQuartoById(id));
        return mv;
    }

    @PostMapping("/quarto/editar")
    public ModelAndView editandoQuarto(@Validated Quarto quarto, Errors errors) {
        ModelAndView mv = new ModelAndView("quarto/quartoEditar");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "O Quarto foi atualizado com sucesso!");
        quarto.setImoveis(serv.findQuartoById(quarto.getId()).getImoveis());
        serv.saveQuarto(quarto);
        mv.addObject("quarto", quarto);
        return mv;
    }

    @GetMapping("/quarto/excluir/{id}")
    public ModelAndView excluirQuarto(@PathVariable Long id, RedirectAttributes ra) {
        if (serv.findQuartoById(id).getImoveis().size() > 0) {
            ra.addFlashAttribute("customMessage", "Não é possível excluir um quarto com imóveis vinculados");
            return new ModelAndView("redirect:/classificador/quartos");
        }
        serv.excluirQuartoById(id);
        ra.addFlashAttribute("sucesso", "O Quarto foi excluído com sucesso.");
        return new ModelAndView("redirect:/classificador/quartos");
    }

    @GetMapping("/quarto/pesquisa")
    public ModelAndView pesquisarQuarto(Integer pesquisa) {
        ModelAndView mv = new ModelAndView("quarto/quartos");
        mv.addObject("quartos", serv.findQuartoByQuantidade(pesquisa));
        if (pesquisa == null) {
            return mv;
        }
        mv.addObject("texto_busca", " contendo " + pesquisa);
        return mv;
    }

}
