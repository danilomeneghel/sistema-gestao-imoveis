package imobiliaria.Controller;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    
				SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
			mv = new ModelAndView("indexUser.html");
		}else if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			mv = new ModelAndView("indexPage.html");
		}
		mv.addObject("index",true);
		return mv;		
	}
}
