package imoveis.controller;

import imoveis.model.*;
import imoveis.service.ClassificadorService;
import imoveis.service.ImovelService;
import imoveis.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/listar/imoveis-usuario")
    public ResponseEntity<List<Imovel>> listarImoveisUsuario() {
        return new ResponseEntity<>(iServ.findAllImoveis(), HttpStatus.OK);
    }

    @GetMapping("/todos/imoveis-usuario")
    public ModelAndView mostrarImoveisUsuario() {
        ModelAndView mv = new ModelAndView("imovel/imoveisUsuario");
        mv.addObject("imoveis", iServ.findAllImoveis());
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
        addObj(mv);
        mv.addObject("imovel", new Imovel());
        return mv;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrandoImovel(@Validated Imovel imovel, Errors errors, Long idEstado, Long idMunicipio) {
        ModelAndView mv = new ModelAndView("imovel/imovelCadastro");
        System.out.println(imovel);
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
        iServ.saveImovel(imovel);

        mv.addObject("sucesso", "O Imóvel foi cadastrado com sucesso.");
        mv.addObject("imovel", new Imovel());

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarBairro(@PathVariable Long id) {
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

    @PostMapping("/editar")
    public ModelAndView editandoImovel(@Validated Imovel imovel, Errors errors, Long idEstado, Long idMunicipio) {
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
        iServ.saveImovel(imovel);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluirImovel(@PathVariable Long id, RedirectAttributes ra) {
        Imovel imovel = iServ.findImovelById(id);
        if (imovel != null) {
            iServ.excluirImovelById(id);
            ra.addFlashAttribute("sucesso", "O Imóvel foi excluído com sucesso.");
        } else {
            ra.addFlashAttribute("erro", "O Imóvel não foi encontrado.");
        }
        return new ModelAndView("redirect:/imovel/todos");
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
