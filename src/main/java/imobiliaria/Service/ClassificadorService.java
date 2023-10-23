package imobiliaria.Service;

import imobiliaria.Entity.CategoriaEntity;
import imobiliaria.Entity.NegocioEntity;
import imobiliaria.Entity.QuartoEntity;
import imobiliaria.Model.Categoria;
import imobiliaria.Model.Negocio;
import imobiliaria.Model.Quarto;
import imobiliaria.Repository.CategoriaRepository;
import imobiliaria.Repository.NegocioRepository;
import imobiliaria.Repository.QuartoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassificadorService {

    @Autowired
    private CategoriaRepository catRep;

    @Autowired
    private NegocioRepository negRep;

    @Autowired
    private QuartoRepository quaRep;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Categoria> findAllCategorias() {
        List<CategoriaEntity> categorias = catRep.findAll();
        return categorias.stream().map(entity -> modelMapper.map(entity, Categoria.class)).collect(Collectors.toList());
    }

    public Categoria findCategoriaById(Long id) {
        Optional<CategoriaEntity> categoria = catRep.findById(id);
        if(!categoria.isEmpty()) {
            return modelMapper.map(categoria.get(), Categoria.class);
        }
        return null;
    }

    public Categoria saveCategoria(Categoria categoria) {
        CategoriaEntity categoriaEntity = modelMapper.map(categoria, CategoriaEntity.class);
        CategoriaEntity saveCategoria = catRep.save(categoriaEntity);
        return modelMapper.map(saveCategoria, Categoria.class);
    }

    public void excluirCategoriaById(Long id) {
        catRep.deleteById(id);
    }

    public List<Categoria> findCategoriaByNome(String nome) {
        List<CategoriaEntity> categorias = catRep.findByNomeContainingIgnoreCase(nome);
        return categorias.stream().map(entity -> modelMapper.map(entity, Categoria.class)).collect(Collectors.toList());
    }

    public List<Negocio> findAllNegocios() {
        List<NegocioEntity> negocios = negRep.findAll();
        return negocios.stream().map(entity -> modelMapper.map(entity, Negocio.class)).collect(Collectors.toList());
    }

    public Negocio findNegocioById(Long id) {
        NegocioEntity negocio = negRep.findById(id).get();
        return modelMapper.map(negocio, Negocio.class);
    }

    public Negocio saveNegocio(Negocio negocio) {
        NegocioEntity negocioEntity = modelMapper.map(negocio, NegocioEntity.class);
        NegocioEntity saveNegocio = negRep.save(negocioEntity);
        return modelMapper.map(saveNegocio, Negocio.class);
    }

    public void excluirNegocioById(Long id) {
        negRep.deleteById(id);
    }

    public List<Negocio> findNegocioByNome(String nome) {
        List<NegocioEntity> negocios = negRep.findByNomeContainingIgnoreCase(nome);
        return negocios.stream().map(entity -> modelMapper.map(entity, Negocio.class)).collect(Collectors.toList());
    }

    public List<Quarto> findAllQuartos() {
        List<QuartoEntity> quartos = quaRep.findAll();
        return quartos.stream().map(entity -> modelMapper.map(entity, Quarto.class)).collect(Collectors.toList());
    }

    public Quarto findQuartoById(Long id) {
        QuartoEntity quarto = quaRep.findById(id).get();
        return modelMapper.map(quarto, Quarto.class);
    }

    public Quarto saveQuarto(Quarto quarto) {
        QuartoEntity quartoEntity = modelMapper.map(quarto, QuartoEntity.class);
        QuartoEntity saveQuarto = quaRep.save(quartoEntity);
        return modelMapper.map(saveQuarto, Quarto.class);
    }

    public void excluirQuartoById(Long id) {
        quaRep.deleteById(id);
    }

    public List<Quarto> findQuartoByQuantidade(Integer quantidade) {
        List<QuartoEntity> quartos = quaRep.findByQuantidade(quantidade);
        return quartos.stream().map(entity -> modelMapper.map(entity, Quarto.class)).collect(Collectors.toList());
    }

}
