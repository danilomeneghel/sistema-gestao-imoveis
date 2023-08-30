package imobiliaria.Controller;

import imobiliaria.Configuration.WebSecurityConfiguration;
import imobiliaria.Entity.User;
import imobiliaria.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

	@Autowired
	private MyUserDetailsService userServ;

	@GetMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView("security/login");
		return mv;
	}

	@GetMapping("/signup")
	public ModelAndView cadastrarUsuario() {
		ModelAndView mv = new ModelAndView("security/signup");
		mv.addObject("user",new User());
		return mv;
	}
	
	@PostMapping("/signup")
	public ModelAndView cadastrando(@Validated User user, Errors errors) {
		ModelAndView mv = new ModelAndView("redirect:/");
		boolean erro = false;
		if(userServ.emailExistente(user.getEmail())) {
			mv.addObject("customMessage","O email já foi cadastrado");
			mv.addObject("erroEmail",true);
			erro = true;
		}
		if(errors.hasErrors()||erro) {
			mv.setViewName("security/signup");
			return mv;
		}
		user.setActive(true);
		user.setRoles("ROLE_USER");
		userServ.saveNewUser(user);

		return mv;
	}

}
