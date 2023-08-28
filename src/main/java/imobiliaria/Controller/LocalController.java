package imobiliaria.Controller;

import java.util.ArrayList;
import java.util.List;

import imobiliaria.Entity.Bairro;
import imobiliaria.Entity.Estado;
import imobiliaria.Entity.Municipio;
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

import imobiliaria.Service.LocalService;

@Controller
@RequestMapping("/local")
public class LocalController {

	@Autowired
	private LocalService localService;
	
	@GetMapping("/estados")
	public ModelAndView showEstados() {
		ModelAndView mv = new ModelAndView("estado/estados");
		mv.addObject("estados",localService.findAllEstados());
		return mv;
	}
	
	@GetMapping("/estado/cadastro")
	public ModelAndView cadastroEstados() {
		ModelAndView  mv = new ModelAndView("estado/estadoCadastro");
		mv.addObject("estado",new Estado());
		return mv;
	}
	
	@PostMapping("/estado/cadastroPreenchido")
	public ModelAndView cadastrarEstado(@Validated Estado estado, Errors errors) {
		ModelAndView mv = new ModelAndView("estado/estadoCadastro");
		if(errors.hasErrors()) {
			return mv;
		}
		mv.addObject("sucesso","O Estado Foi Cadastrado com Sucesso!");
		localService.saveEstado(estado);
		mv.addObject("estado",new Estado());
		return mv;
	}
	
	@GetMapping("/estado/editar/{id}")
	public ModelAndView editarEstado(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("estado/estadoEditar");
		mv.addObject("estado",localService.findEstadoById(id));
		return mv;
	}
	
	@PostMapping("/estado/editar")
	public ModelAndView editandoEstado(@Validated Estado estado,Errors errors) {
		ModelAndView mv = new ModelAndView("estado/estadoEditar");
		if(errors.hasErrors()) {
			return mv;
		}
		mv.addObject("sucesso","O estado foi atualizado com sucesso!");
		estado.setMunicipios(localService.findEstadoById(estado.getId()).getMunicipios());
		localService.saveEstado(estado);
		return mv;
	}
	
	@GetMapping("/estado/excluir/{id}")
	public ModelAndView excluirEstado(@PathVariable Long id,RedirectAttributes ra) {
		if(localService.findEstadoById(id).getMunicipios().size()>0) {
			ra.addFlashAttribute("customMessage","Não é possível excluir um Estado com municípios vinculados.");
			return new ModelAndView("redirect:/local/estados");
		}
		localService.excluirEstadoById(id);
		ra.addFlashAttribute("sucesso","O Estado foi excluído com sucesso");
		return new ModelAndView("redirect:/local/estados");
	}

	@GetMapping("/estado/pesquisa")
    public ModelAndView pesquisarEstado(String pesquisa) {
        ModelAndView mv = new ModelAndView("estado/estados");
        mv.addObject("estados", localService.findEstadoByNome(pesquisa));
        if(pesquisa.isBlank()) {
        	return mv;
        }
        mv.addObject("texto_busca"," contendo " + pesquisa);
        return mv;
    }
	
	@GetMapping("/municipios")
	public ModelAndView showMunicipios() {
		ModelAndView mv = new ModelAndView("municipio/municipios");
		mv.addObject("municipios",localService.findAllMunicipios());
		return mv;
	}
	
	@GetMapping("/municipio/cadastro")
	public ModelAndView cadastroMunicipio() {
		ModelAndView  mv = new ModelAndView("municipio/municipioCadastro");
		Municipio mp = new Municipio();
		Estado et = new Estado();
		mp.setEstado(et);
		mv.addObject("municipio",mp);
		mv.addObject("estados",localService.findAllEstados());
		return mv;
	}
	
	@PostMapping("/municipio/cadastroPreenchido")
	public ModelAndView cadastrarMunicipio(@Validated Municipio municipio, Errors errors) {
		ModelAndView mv = new ModelAndView("municipio/municipioCadastro");
		mv.addObject("estados",localService.findAllEstados());
		Long id = municipio.getEstado().getId();
		if(errors.hasErrors() || id==null) {
			if(id==null) {
				mv.addObject("customMessage","O estado escolhido deve ser válido.");
			}
			return mv;
		}
		mv.addObject("sucesso","O Município Foi Cadastrado com Sucesso!");
		
		Estado estado = localService.findEstadoById(id);
		municipio.setEstado(estado);
		localService.saveMunicipio(municipio);

		mv.addObject("municipio",new Municipio());
		return mv;
	}
	
	@GetMapping("/municipio/editar/{id}")
	public ModelAndView editarMunicipio(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("municipio/municipioEditar");
		mv.addObject("estados",localService.findAllEstados());
		mv.addObject("municipio",localService.findMunicipioById(id));
		return mv;
	}
	
	@PostMapping("/municipio/editar")
	public ModelAndView editandoMunicipio(@Validated Municipio municipio,Errors errors) {
		ModelAndView mv = new ModelAndView("municipio/municipioEditar");
		mv.addObject("estados",localService.findAllEstados());
		Long id = municipio.getEstado().getId();

		if(errors.hasErrors() || id==null) {
			if(id==null) {
				mv.addObject("customMessage","O estado escolhido deve ser válido.");
			}
			return mv;
		}
		municipio.setBairros(localService.findMunicipioById(municipio.getId()).getBairros());
		localService.saveMunicipio(municipio);
		mv.addObject("sucesso","O município foi atualizado com sucesso!");
		return mv;
	}
	
	@GetMapping("/municipio/excluir/{id}")
	public ModelAndView excluirMunicipio(@PathVariable Long id,RedirectAttributes ra) {
		if(localService.findMunicipioById(id).getBairros().size()>0) {
			ra.addFlashAttribute("customMessage","Não é possível excluir um Município com bairros vinculados.");
			return new ModelAndView("redirect:/local/municipios");
		}
		
		localService.excluirMunicipioById(id);
		ra.addFlashAttribute("sucesso","O município foi excluído com sucesso");
		return new ModelAndView("redirect:/local/municipios");
	}
	
	@GetMapping("/municipio/pesquisa")
    public ModelAndView pesquisarMunicipio(String pesquisa) {
        ModelAndView mv = new ModelAndView("municipio/municipios");
        mv.addObject("municipios", localService.findMunicipioByNome(pesquisa));
        if(pesquisa.isBlank()) {
        	return mv;
        }
        mv.addObject("texto_busca"," contendo " + pesquisa);
        return mv;
    }

	@GetMapping("/bairros")
	public ModelAndView showBairros() {
		ModelAndView mv = new ModelAndView("bairro/bairros");
		mv.addObject("bairros",localService.findAllBairros());
		return mv;
	}
	
	@GetMapping("/bairro/cadastro")
	public ModelAndView cadastroBairro() {
		ModelAndView  mv = new ModelAndView("bairro/bairroCadastro");
		mv.addObject("bairro",new Bairro());
		mv.addObject("idEstado",null);
		mv.addObject("estados",localService.findAllEstados());
		mv.addObject("municipios",localService.findAllMunicipios());
		return mv;
	}
	
	@PostMapping("/bairro/cadastroPreenchido")
	public ModelAndView cadastrarBairro(@Validated Bairro bairro, Errors errors, Integer idEstado) {
		ModelAndView mv = new ModelAndView("bairro/bairroCadastro");
		mv.addObject("estados",localService.findAllEstados());
		mv.addObject("municipios",localService.findAllMunicipios());
		Long id = bairro.getMunicipio().getId();
		List<String> customMessage = new ArrayList<String>();
		boolean erro = false;
		
		if(id==null) {
			customMessage.add("O Municipio escolhido deve ser válido.");
			mv.addObject("municipio_ok",true);
			erro = true;
		}
		if(idEstado==null) {
			customMessage.add("O Estado escolhido deve ser válido.");
			mv.addObject("estado_ok",true);
			erro = true;
		}
		if(errors.hasErrors() || erro) {
			mv.addObject("customMessage",customMessage);
			return mv;
		}
		mv.addObject("sucesso","O Bairro Foi Cadastrado com Sucesso!");
		localService.saveBairro(bairro);
		mv.addObject("bairro",new Bairro());
		return mv;
	}
	
	@GetMapping("/bairro/editar/{id}")
	public ModelAndView editarBairro(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("bairro/bairroEditar");
		Bairro bairro = localService.findBairroById(id);
		mv.addObject("bairro",bairro);
		mv.addObject("estados",localService.findAllEstados());
		mv.addObject("municipios",bairro.getMunicipio().getEstado().getMunicipios());
		return mv;
	}
	
	@PostMapping("/bairro/editar")
	public ModelAndView editandoBairro(@Validated Bairro bairro,Errors errors) {
		ModelAndView mv = new ModelAndView("bairro/bairroEditar");
		mv.addObject("estados",localService.findAllEstados());
		mv.addObject("municipios",localService.findBairroById(bairro.getId()).getMunicipio().getEstado().getMunicipios());
		
		Long idMunicipio = bairro.getMunicipio().getId();
		Long idEstado = bairro.getMunicipio().getEstado().getId();
		List<String> customMessage = new ArrayList<String>();
		boolean erro = false;
		
		if(idMunicipio==null) {
			customMessage.add("O Municipio escolhido deve ser válido.");
			mv.addObject("municipio_ok",true);
			erro = true;
		}
		if(idEstado==null) {
			customMessage.add("O Estado escolhido deve ser válido.");
			mv.addObject("estado_ok",true);
			erro = true;
		}
		if(errors.hasErrors() || erro) {
			mv.addObject("customMessage",customMessage);
			return mv;
		}
		
		mv.addObject("sucesso","O bairro foi atualizado com sucesso!");
		localService.saveBairro(bairro);
		mv.addObject("municipios",localService.findBairroById(bairro.getId()).getMunicipio().getEstado().getMunicipios());
		return mv;
	}
	
	@GetMapping("/bairro/excluir/{id}")
	public ModelAndView excluirBairro(@PathVariable Long id,RedirectAttributes ra) {
		if(localService.findBairroById(id).getImoveis().size()>0) {
			ra.addFlashAttribute("customMessage","Não é possível excluir um bairro com imóveis vinculados.");
			return new ModelAndView("redirect:/local/bairros");
		}
		localService.excluirBairroById(id);
		ra.addFlashAttribute("sucesso","O bairro foi excluído com sucesso");
		return new ModelAndView("redirect:/local/bairros");
	}
	
	@GetMapping("/bairro/pesquisa")
    public ModelAndView pesquisarBairro(String pesquisa) {
        ModelAndView mv = new ModelAndView("bairro/bairros");
        mv.addObject("bairros", localService.findBairroByNome(pesquisa));
        if(pesquisa.isBlank()) {
        	return mv;
        }
        mv.addObject("texto_busca"," contendo " + pesquisa);
        return mv;
    }
}
