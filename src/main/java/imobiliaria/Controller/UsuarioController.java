package imobiliaria.Controller;

import imobiliaria.Enums.Role;
import imobiliaria.Model.Usuario;
import imobiliaria.Service.*;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService uServ;

    @GetMapping("/todos")
    public ModelAndView mostrarUsuarios() {
        ModelAndView mv = new ModelAndView("usuario/usuarios");
        mv.addObject("usuarios", uServ.findAllUsuarios());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastroUsuario() {
        ModelAndView mv = new ModelAndView("usuario/usuarioCadastro");
        mv.addObject("usuario", new Usuario());
        mv.addObject("roles", Role.values());
        return mv;
    }

    @PostMapping("/cadastro-preenchido")
    public ModelAndView cadastrarUsuario(@Validated Usuario usuario, Errors errors) {
        ModelAndView mv = new ModelAndView("usuario/usuarioCadastro");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "A Usuario foi cadastrada com sucesso!");
        uServ.saveUsuario(usuario);
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarUsuario(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("usuario/usuarioEditar");
        Usuario usuario = uServ.findUsuarioById(id);
        mv.addObject("usuario", usuario);
        mv.addObject("tipoRole", usuario.getRoles());
        mv.addObject("roles", Role.values());
        return mv;
    }

    @PostMapping("/editar")
    public ModelAndView editandoUsuario(@Validated Usuario usuario, Errors errors) {
        ModelAndView mv = new ModelAndView("usuario/usuarioEditar");
        if (errors.hasErrors()) {
            return mv;
        }
        mv.addObject("sucesso", "A Usuario foi atualizada com sucesso!");
        uServ.saveUsuario(usuario);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluirUsuario(@PathVariable Long id, RedirectAttributes ra) {
        Usuario usuario = uServ.findUsuarioById(id);
        if(usuario != null) {
            uServ.excluirUsuarioById(id);
            ra.addFlashAttribute("sucesso", "A Usuario foi excluído com sucesso.");
        } else {
            ra.addFlashAttribute("erro", "A Usuario não foi encontrado.");
        }
        return new ModelAndView("redirect:/usuario/todos");
    }

    @GetMapping("/pesquisa")
    public ModelAndView pesquisar(String pesquisa) {
        ModelAndView mv = new ModelAndView("usuario/usuarios");
        mv.addObject("usuarios", uServ.findUsuarioByUsername(pesquisa));
        if (pesquisa.isBlank()) {
            return mv;
        }
        mv.addObject("texto_busca", " contendo " + pesquisa);
        return mv;
    }

}
