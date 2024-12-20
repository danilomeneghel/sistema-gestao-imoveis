package imoveis.controller;

import imoveis.model.*;
import imoveis.service.ClassificadorService;
import imoveis.service.ImovelService;
import imoveis.service.LocalidadeService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/imovel")
public class ImovelController {

    @Autowired
    private ImovelService iServ;

    @Autowired
    private LocalidadeService lServ;

    @Autowired
    private ClassificadorService cServ;

    @GetMapping("/imoveis-disponiveis")
    public ModelAndView homeImoveisUsuario() {
        ModelAndView mv = new ModelAndView("indexUsuario");
        mv.addObject("categorias", cServ.findAllCategorias());
        mv.addObject("imoveis", iServ.findImoveisByAtivo());
        return mv;
    }

    @GetMapping("/imoveis-disponiveis/{idCategoria}")
    public ModelAndView filtrarImoveisUsuario(@PathVariable Long idCategoria) {
        ModelAndView mv = new ModelAndView("imovel/imoveisFiltro");
        if (idCategoria != 0) {
            Categoria categoria = cServ.findCategoriaById(idCategoria);
            mv.addObject("imoveis", iServ.findImoveisByAtivoAndCategoria(categoria));
        } else {
            mv.addObject("imoveis", iServ.findImoveisByAtivo());
        }
        mv.addObject("categorias", cServ.findAllCategorias());
        return mv;
    }

    @GetMapping("/todos")
    public ModelAndView mostrarImoveis() {
        ModelAndView mv = new ModelAndView("imovel/imoveis");
        mv.addObject("imoveis", iServ.findAllImoveis());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastroImovel() {
        ModelAndView mv = new ModelAndView("imovel/imovelCadastro");

        List<Categoria> categorias = cServ.findAllCategorias();
        List<Negocio> negocios = cServ.findAllNegocios();
        List<Quarto> quartos = cServ.findAllQuartos();
        List<Bairro> bairros = lServ.findAllBairros();
        List<Municipio> municipios = lServ.findAllMunicipios();
        List<Estado> estados = lServ.findAllEstados();

        addObj(mv);
        mv.addObject("categorias", categorias);
        mv.addObject("negocios", negocios);
        mv.addObject("quartos", quartos);
        mv.addObject("bairros", bairros);
        mv.addObject("municipios", municipios);
        mv.addObject("estados", estados);
        mv.addObject("imovel", new Imovel());
        return mv;
    }

    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ModelAndView cadastrarImovel(@Validated Imovel imovel, Errors errors, Long idEstado, Long idMunicipio) {
        ModelAndView mv = new ModelAndView("imovel/imovelCadastro");
        addObj(mv);
        boolean erro = false;
        List<String> customMessage = new ArrayList<String>();
        Long idNeg = imovel.getNegocio().getId();
        Long idCat = imovel.getCategoria().getId();
        Long idQua = imovel.getQuarto().getId();

        mv.addObject("idEstado", idEstado);
        mv.addObject("idMunicipio", idMunicipio);

        if (imovel.getBairro() == null) {
            customMessage.add("O Bairro selecionado deve ser válido.");
            mv.addObject("erroBairro", true);
            erro = true;
        } else if (imovel.getBairro().getId() == null) {
            customMessage.add("O Bairro selecionado deve ser válido.");
            mv.addObject("erroBairro", true);
            erro = true;
        }
        if (idNeg == null) {
            customMessage.add("O Negócio selecionado deve ser válido.");
            mv.addObject("erroNegocio", true);
            erro = true;
        }
        if (idCat == null) {
            customMessage.add("A Categoria selecionada deve ser válida.");
            mv.addObject("erroCategoria", true);
            erro = true;
        }
        if (idQua == null) {
            customMessage.add("O Quarto selecionado deve ser válido.");
            mv.addObject("erroQuarto", true);
            erro = true;
        }
        if (idEstado == null) {
            customMessage.add("O Estado selecionado deve ser válido.");
            mv.addObject("erroEstado", true);
            erro = true;
        } else {
            List<Municipio> municipios = lServ.findMunicipioPerEstado(idEstado);
            mv.addObject("municipios", municipios);
            if (municipios == null) {
                mv.addObject("semMunicipios", true);
            }
        }
        if (idMunicipio == null) {
            customMessage.add("O Municipio selecionado deve ser válido.");
            mv.addObject("erroMunicipio", true);
            erro = true;
        } else {
            List<Bairro> bairros = lServ.findBairroPerMunicipio(idMunicipio);
            mv.addObject("bairros", bairros);
            if (bairros == null) {
                mv.addObject("semBairros", true);
            }
        }
        if (errors.hasErrors() || erro) {
            mv.addObject("customMessage", customMessage);
            return mv;
        }
        iServ.salvarImovel(imovel);

        mv.addObject("sucesso", "O Imóvel foi cadastrado com sucesso.");
        mv.addObject("imovel", new Imovel());

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editaImovel(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("imovel/imovelEditar");

        Imovel imovel = iServ.findImovelById(id);
        List<Quarto> quartos = cServ.findAllQuartos();
        List<Bairro> bairros = lServ.findAllBairros();
        List<Municipio> municipios = lServ.findAllMunicipios();
        List<Estado> estados = lServ.findAllEstados();

        addObj(mv);
        mv.addObject("imovel", imovel);
        mv.addObject("quartos", quartos);
        mv.addObject("bairros", bairros);
        mv.addObject("municipios", municipios);
        mv.addObject("estados", estados);
        mv.addObject("idQuarto", imovel.getQuarto().getId());
        mv.addObject("idBairro", imovel.getBairro().getId());
        mv.addObject("idMunicipio", imovel.getBairro().getMunicipio().getId());
        mv.addObject("idEstado", imovel.getBairro().getMunicipio().getEstado().getId());
        return mv;
    }

    @PostMapping(value = "/editar", consumes = "multipart/form-data")
    public ModelAndView editarImovel(@Validated Imovel imovel, Errors errors, Long idEstado, Long idMunicipio) {
        ModelAndView mv = new ModelAndView("imovel/imovelEditar");
        boolean erro = false;
        addObj(mv);
        List<String> customMessage = new ArrayList<String>();
        Long idNeg = imovel.getNegocio().getId();
        Long idCat = imovel.getCategoria().getId();
        Long idQua = imovel.getQuarto().getId();
        Long idBai = imovel.getBairro().getId();

        if (idEstado == null) {
            customMessage.add("O Estado selecionado deve ser válido.");
            mv.addObject("erroEstado", true);
            erro = true;
        } else {
            mv.addObject("municipios", lServ.findMunicipioPerEstado(idEstado));
            mv.addObject("idEstado", idEstado);
        }
        if (idMunicipio == null) {
            customMessage.add("O Municipio selecionado deve ser válido.");
            mv.addObject("erroMunicipio", true);
            erro = true;
        } else {
            mv.addObject("bairros", lServ.findBairroPerMunicipio(idMunicipio));
            mv.addObject("idMunicipio", idMunicipio);
        }
        if (idNeg == null) {
            customMessage.add("O Negócio selecionado deve ser válido.");
            mv.addObject("erroNegocio", true);
            erro = true;
        }
        if (idCat == null) {
            customMessage.add("A Categoria selecionada deve ser válida.");
            mv.addObject("erroCategoria", true);
            erro = true;
        }
        if (idQua == null) {
            customMessage.add("O Quarto selecionado deve ser válido.");
            mv.addObject("erroQuarto", true);
            erro = true;
        }
        if (idBai == null) {
            customMessage.add("O Bairro selecionado deve ser válido.");
            mv.addObject("erroBairro", true);
            erro = true;
        }
        if (errors.hasErrors() || erro) {
            mv.addObject("customMessage", customMessage);
            return mv;
        }
        mv.addObject("sucesso", "O imovel foi atualizado com sucesso!");
        imovel.setImagens(iServ.findImovelById(imovel.getId()).getImagens());
        iServ.salvarImovel(imovel);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluirImovel(@PathVariable Long id, RedirectAttributes ra) {
        if (iServ.findImovelById(id) != null) {
            iServ.excluirImovelById(id);
            ra.addFlashAttribute("sucesso", "O Imóvel foi excluído com sucesso.");
        } else {
            ra.addFlashAttribute("erro", "O Imóvel não foi encontrado.");
        }
        return new ModelAndView("redirect:/imovel/todos");
    }

    @GetMapping("/visualizar/imovel-usuario/{id}")
    public ModelAndView visualizarImovelUsuario(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("imovel/imovelVisualizarUsuario");
        mv.addObject("imovel", iServ.findImovelById(id));
        return mv;
    }

    @GetMapping("/visualizar/{id}")
    public ModelAndView visualizarImovel(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("imovel/imovelVisualizar");
        mv.addObject("imovel", iServ.findImovelById(id));
        return mv;
    }

    private void addObj(ModelAndView mv) {
        mv.addObject("negocios", cServ.findAllNegocios());
        mv.addObject("categorias", cServ.findAllCategorias());
        mv.addObject("quartos", cServ.findAllQuartos());
        mv.addObject("estados", lServ.findAllEstados());
        mv.addObject("municipios", lServ.findAllMunicipios());
        mv.addObject("bairros", lServ.findAllBairros());
    }

}
