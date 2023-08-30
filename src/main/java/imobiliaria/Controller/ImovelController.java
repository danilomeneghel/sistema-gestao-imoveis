package imobiliaria.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import imobiliaria.Entity.Imovel;
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

import imobiliaria.Service.ClassificadorService;
import imobiliaria.Service.ImovelService;
import imobiliaria.Service.LocalService;

@Controller
@RequestMapping("/imovel")
public class ImovelController {

	@Autowired
	private ImovelService iServ;
	
	@Autowired
	private LocalService lServ;
	
	@Autowired
	private ClassificadorService cServ;
	
	@GetMapping("/todos")
	public ModelAndView mostrarImoveis() {
		ModelAndView mv = new ModelAndView("imovel/imoveis");
		mv.addObject("imobiliaria",iServ.findAllImoveis());
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastroImovel() {
		ModelAndView mv = new ModelAndView("imovel/imovelCadastro");
		addObj(mv);
		mv.addObject("imovel",new Imovel());
		return mv;
	}
	
	@PostMapping("/cadastroPreenchido")
	public ModelAndView cadastrandoImovel(@Validated Imovel imovel,Errors errors,Long idEstado,Long idMunicipio) {
		ModelAndView mv = new ModelAndView("imovel/imovelCadastro");
		System.out.println(imovel);
		addObj(mv);
		boolean erro = false;
		List<String> customMessage = new ArrayList<String>();
		Long idNeg = imovel.getNegocio().getId(); 
		Long idCat = imovel.getCategoria().getId();
		Long idQua = imovel.getQuartos().getId();
		
		
		mv.addObject("idEstado",idEstado);
		mv.addObject("idMunicipio",idMunicipio);
		
		if(imovel.getBairro()==null) {
			customMessage.add("O Bairro selecionado deve ser válido.");
			mv.addObject("erroBairro",true);
			erro=true;
		}else if(imovel.getBairro().getId()==null) {
			customMessage.add("O Bairro selecionado deve ser válido.");
			mv.addObject("erroBairro",true);
			erro=true;
		}
		if(idNeg==null) {
			customMessage.add("O Negócio selecionado deve ser válido.");
			mv.addObject("erroNegocio",true);
			erro=true;
		}
		if(idCat==null) {
			customMessage.add("A Categoria selecionada deve ser válida.");
			mv.addObject("erroCategoria",true);
			erro=true;
		}
		if(idQua==null) {
			customMessage.add("O Quarto selecionado deve ser válido.");
			mv.addObject("erroQuarto",true);
			erro=true;
		}
		if(idEstado==null) {
			customMessage.add("O Estado selecionado deve ser válido.");
			mv.addObject("erroEstado",true);
			erro=true;
		}else {
			mv.addObject("municipios",lServ.findEstadoById(idEstado).getMunicipios());
			if(lServ.findEstadoById(idEstado).getMunicipios().size()==0) {
				mv.addObject("semMunicipios",true);
			}
		}
		if(idMunicipio==null) {
			customMessage.add("O Municipio selecionado deve ser válido.");
			mv.addObject("erroMunicipio",true);
			erro=true;
		}else {
			mv.addObject("bairros",lServ.findMunicipioById(idMunicipio).getBairros());
			if(lServ.findMunicipioById(idMunicipio).getBairros().size()==0) {
				mv.addObject("semBairros",true);
			}
		}
		if(errors.hasErrors() || erro) {
			mv.addObject("customMessage",customMessage);
			return mv;
		}
		iServ.saveImovel(imovel);
		
		mv.addObject("sucesso","O Imóvel foi cadastrado com sucesso.");
		mv.addObject("imovel",new Imovel());
		
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editarBairro(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("imovel/imovelEditar");
		Imovel imovel = iServ.findImovelById(id);
		mv.addObject("imovel",imovel);
		addObj(mv);
		mv.addObject("municipios",imovel.getBairro().getMunicipio().getEstado().getMunicipios());
		mv.addObject("bairros",imovel.getBairro().getMunicipio().getBairros());
		mv.addObject("idEstado",imovel.getBairro().getMunicipio().getEstado().getId());
		mv.addObject("idMunicipio",imovel.getBairro().getMunicipio().getId());
		return mv;
	}
	
	@PostMapping("/editar")
	public ModelAndView editandoImovel(@Validated Imovel imovel,Errors errors,Long idEstado, Long idMunicipio) {
		ModelAndView mv = new ModelAndView("imovel/imovelEditar");
		boolean erro = false;
		addObj(mv);
		List<String> customMessage = new ArrayList<String>();
		Long idNeg = imovel.getNegocio().getId(); 
		Long idCat = imovel.getCategoria().getId();
		Long idQua = imovel.getQuartos().getId();
		Long idBai = imovel.getBairro().getId();
		
		if(idEstado==null) {
			customMessage.add("O Estado selecionado deve ser válido.");
			mv.addObject("erroEstado",true);
			erro=true;
		}else {
			mv.addObject("municipios",lServ.findEstadoById(idEstado).getMunicipios());
			mv.addObject("idEstado",idEstado);
		}
		if(idMunicipio==null) {
			customMessage.add("O Municipio selecionado deve ser válido.");
			mv.addObject("erroMunicipio",true);
			erro=true;
		}else {
			mv.addObject("bairros",lServ.findMunicipioById(idMunicipio).getBairros());		
			mv.addObject("idMunicipio",idMunicipio);
		}
		if(idNeg==null) {
			customMessage.add("O Negócio selecionado deve ser válido.");
			mv.addObject("erroNegocio",true);
			erro=true;
		}
		if(idCat==null) {
			customMessage.add("A Categoria selecionada deve ser válida.");
			mv.addObject("erroCategoria",true);
			erro=true;
		}
		if(idQua==null) {
			customMessage.add("O Quarto selecionado deve ser válido.");
			mv.addObject("erroQuarto",true);
			erro=true;
		}
		if(idBai==null) {
			customMessage.add("O Bairro selecionado deve ser válido.");
			mv.addObject("erroBairro",true);
			erro=true;
		}
		if(errors.hasErrors() || erro) {
			mv.addObject("customMessage",customMessage);
			return mv;
		}
		mv.addObject("sucesso","O imovel foi atualizado com sucesso!");
		imovel.setImagens(iServ.findImovelById(imovel.getId()).getImagens());
		iServ.saveImovel(imovel);
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirImovel(@PathVariable Long id,RedirectAttributes ra) {
		iServ.excluirImovelById(id);
		ra.addFlashAttribute("sucesso","O Imóvel foi excluido com sucesso.");
		return new ModelAndView("redirect:/imovel/todos");
	}
	
	@GetMapping("/pesquisar")
	public ModelAndView pesquisarImovel() {
		ModelAndView mv = new ModelAndView("imovel/imovelPesquisar");
		addObj(mv);
		mv.addObject("imovel",new Imovel());
		return mv;
	}
	
	@GetMapping("/pesquisarResultado")
	public ModelAndView pesquisaRealizadaImovel(Imovel imovel, Long idEstado, Long idMunicipio,
			BigDecimal valorMinimo, BigDecimal valorMaximo) {
		ModelAndView mv = new ModelAndView("imovel/imovelPesquisar");
		addObj(mv);
		mv.addObject("idEstado",idEstado);			
		mv.addObject("idMunicipio",idMunicipio);
		mv.addObject("valorMinimo",valorMinimo);
		mv.addObject("valorMaximo",valorMaximo);
		
		if(idEstado!=null) {
			mv.addObject("municipios",lServ.findEstadoById(idEstado).getMunicipios());
		}
		if(idMunicipio!=null) {
			mv.addObject("bairros",lServ.findMunicipioById(idMunicipio).getBairros());
		}
		mv.addObject("imovel",imovel);
		mv.addObject("imobiliaria",iServ.findImovelByExample(imovel, idMunicipio, idEstado,valorMinimo,valorMaximo));
		return mv;
	}
	
	@GetMapping("/visualizar/{id}")
	public ModelAndView visualizarImovel(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("imovel/imovelVisualizar");
		mv.addObject("imovel",iServ.findImovelById(id));
		return mv;
	}
	
	
	private void addObj(ModelAndView mv) {
		mv.addObject("negocios", cServ.findAllNegocio());
		mv.addObject("categorias",cServ.findAllCategoria());
		mv.addObject("quartos",cServ.findAllQuartos());
		mv.addObject("estados",lServ.findAllEstados());
		mv.addObject("municipios",lServ.findAllMunicipios());
		mv.addObject("bairros",lServ.findAllBairros());
	}
}
