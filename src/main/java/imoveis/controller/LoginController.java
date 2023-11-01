package imoveis.controller;

import imoveis.model.Usuario;
import imoveis.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mv = new ModelAndView("security/login");
        return mv;
    }

    @GetMapping("/signup")
    public ModelAndView cadastrarUsuario() {
        ModelAndView mv = new ModelAndView("security/signup");
        mv.addObject("user", new Usuario());
        return mv;
    }

    @PostMapping("/signup")
    public ModelAndView cadastrando(@Validated Usuario usuario, Errors errors) {
        ModelAndView mv = new ModelAndView("redirect:/");
        boolean erro = false;
        if (usuarioService.emailExistente(usuario.getEmail())) {
            mv.addObject("customMessage", "O e-mail já foi cadastrado");
            mv.addObject("erroEmail", true);
            erro = true;
        }
        if (errors.hasErrors() || erro || usuario.getName().isEmpty() || usuario.getUsername().isEmpty() || usuario.getPassword().isEmpty()) {
            mv.addObject("customMessage", "Revise os campos obrigatórios");
            mv.setViewName("security/signup");
            return mv;
        }
        usuario.setActive(true);
        usuario.setRoles("ROLE_USER");
        usuarioService.saveUsuario(usuario);

        return mv;
    }

}
