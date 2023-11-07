package imoveis.controller;

import imoveis.model.Categoria;
import imoveis.model.Negocio;
import imoveis.model.Quarto;
import imoveis.service.ClassificadorService;
import imoveis.service.ImovelService;
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
    private ClassificadorService servCla;

    @Autowired
    private ImovelService servImo;

    @GetMapping("/categorias")
    public ModelAndView mostrarCategorias() {
        ModelAndView mv = new ModelAndView("categoria/categorias");
        mv.addObject("categorias", servCla.findAllCategorias());
        return mv;
    }

    @GetMapping("/categoria/cadastro")
    public ModelAndView cadastroCategoria() {
        ModelAndView mv = new ModelAndView("categoria/categoriaCadastro");
        mv.addObject("categoria", new Categoria());
        return mv;
    }

    @PostMapping("/categoria/cadastrar")
    public ModelAndView cadastrarCategoria(@Validated Categoria categoria, Errors errors) {
        ModelAndView mv = new ModelAndView("categoria/categoriaCadastro");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "A Categoria foi cadastrada com sucesso!");
        servCla.salvarCategoria(categoria);
        mv.addObject("categoria", new Categoria());
        return mv;
    }

    @GetMapping("/categoria/editar/{id}")
    public ModelAndView editaCategoria(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("categoria/categoriaEditar");
        mv.addObject("categoria", servCla.findCategoriaById(id));
        return mv;
    }

    @PostMapping("/categoria/editar")
    public ModelAndView editarCategoria(@Validated Categoria categoria, Errors errors) {
        ModelAndView mv = new ModelAndView("categoria/categoriaEditar");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "A Categoria foi atualizada com sucesso!");
        servCla.salvarCategoria(categoria);
        return mv;
    }

    @GetMapping("/categoria/excluir/{id}")
    public ModelAndView excluirCategoria(@PathVariable Long id, RedirectAttributes ra) {
        Categoria categoria = servCla.findCategoriaById(id);
        if (categoria != null) {
            if (!servImo.findImoveisByCategoria(categoria).isEmpty()) {
                ra.addFlashAttribute("customMessage", "Não é possível excluir uma categoria com imóveis vinculados.");
            }
            servCla.excluirCategoria(id);
            ra.addFlashAttribute("sucesso", "A Categoria foi excluída com sucesso.");
        } else {
            ra.addFlashAttribute("erro", "A Categoria não foi encontrada.");
        }
        return new ModelAndView("redirect:/classificador/categorias");
    }

    @GetMapping("/negocios")
    public ModelAndView mostrarNegocios() {
        ModelAndView mv = new ModelAndView("negocio/negocios");
        mv.addObject("negocios", servCla.findAllNegocios());
        return mv;
    }

    @GetMapping("/negocio/cadastro")
    public ModelAndView cadastroNegocio() {
        ModelAndView mv = new ModelAndView("negocio/negocioCadastro");
        mv.addObject("negocio", new Negocio());
        return mv;
    }

    @PostMapping("/negocio/cadastrar")
    public ModelAndView cadastrarNegocio(@Validated Negocio negocio, Errors errors) {
        ModelAndView mv = new ModelAndView("negocio/negocioCadastro");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "O Negócio foi cadastrado com sucesso!");
        servCla.salvarNegocio(negocio);
        mv.addObject("negocio", new Negocio());
        return mv;
    }

    @GetMapping("/negocio/editar/{id}")
    public ModelAndView editaNegocio(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("negocio/negocioEditar");
        mv.addObject("negocio", servCla.findNegocioById(id));
        return mv;
    }

    @PostMapping("/negocio/editar")
    public ModelAndView editarNegocio(@Validated Negocio negocio, Errors errors) {
        ModelAndView mv = new ModelAndView("negocio/negocioEditar");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "O Negócio foi atualizado com sucesso!");
        servCla.salvarNegocio(negocio);
        return mv;
    }

    @GetMapping("/negocio/excluir/{id}")
    public ModelAndView excluirNegocio(@PathVariable Long id, RedirectAttributes ra) {
        Negocio negocio = servCla.findNegocioById(id);
        if (negocio != null) {
            if (!servImo.findImoveisByNegocio(negocio).isEmpty()) {
                ra.addFlashAttribute("customMessage", "Não é possível excluir um negócio com imóveis vinculados.");
            }
            servCla.excluirNegocio(id);
            ra.addFlashAttribute("sucesso", "O Negócio foi excluído com sucesso.");
        } else {
            ra.addFlashAttribute("erro", "O Negócio não foi encontrado.");
        }
        return new ModelAndView("redirect:/classificador/negocios");
    }

    @GetMapping("/quartos")
    public ModelAndView mostrarQuartos() {
        ModelAndView mv = new ModelAndView("quarto/quartos");
        mv.addObject("quartos", servCla.findAllQuartos());
        return mv;
    }

    @GetMapping("/quarto/cadastro")
    public ModelAndView cadastroQuarto() {
        ModelAndView mv = new ModelAndView("quarto/quartoCadastro");
        mv.addObject("quarto", new Quarto());
        return mv;
    }

    @PostMapping("/quarto/cadastrar")
    public ModelAndView cadastrarQuarto(@Validated Quarto quarto, Errors errors) {
        ModelAndView mv = new ModelAndView("quarto/quartoCadastro");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "O Quarto foi cadastrado com sucesso!");
        servCla.salvarQuarto(quarto);
        mv.addObject("quarto", new Quarto());
        return mv;
    }

    @GetMapping("/quarto/editar/{id}")
    public ModelAndView editaQuarto(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("quarto/quartoEditar");
        mv.addObject("quarto", servCla.findQuartoById(id));
        return mv;
    }

    @PostMapping("/quarto/editar")
    public ModelAndView editarQuarto(@Validated Quarto quarto, Errors errors) {
        ModelAndView mv = new ModelAndView("quarto/quartoEditar");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "O Quarto foi atualizado com sucesso!");
        servCla.salvarQuarto(quarto);
        mv.addObject("quarto", quarto);
        return mv;
    }

    @GetMapping("/quarto/excluir/{id}")
    public ModelAndView excluirQuarto(@PathVariable Long id, RedirectAttributes ra) {
        Quarto quarto = servCla.findQuartoById(id);
        if (quarto != null) {
            if (!servImo.findImoveisByQuarto(quarto).isEmpty()) {
                ra.addFlashAttribute("customMessage", "Não é possível excluir um quarto com imóveis vinculados.");
            }
            servCla.excluirQuarto(id);
            ra.addFlashAttribute("sucesso", "O Quarto foi excluído com sucesso.");
        } else {
            ra.addFlashAttribute("erro", "O Quarto não foi encontrado.");
        }
        return new ModelAndView("redirect:/classificador/quartos");
    }

}
