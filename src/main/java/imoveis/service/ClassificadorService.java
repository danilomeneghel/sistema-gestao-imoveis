package imoveis.service;

import imoveis.entity.CategoriaEntity;
import imoveis.entity.NegocioEntity;
import imoveis.entity.QuartoEntity;
import imoveis.model.Categoria;
import imoveis.model.Negocio;
import imoveis.model.Quarto;
import imoveis.repository.CategoriaRepository;
import imoveis.repository.NegocioRepository;
import imoveis.repository.QuartoRepository;
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
        Optional<CategoriaEntity> categoriaEntity = catRep.findById(id);
        if (!categoriaEntity.isEmpty()) {
            return modelMapper.map(categoriaEntity.get(), Categoria.class);
        }
        return null;
    }

    public Categoria salvarCategoria(Categoria categoria) {
        CategoriaEntity categoriaEntity = modelMapper.map(categoria, CategoriaEntity.class);
        CategoriaEntity salvarCategoria = catRep.save(categoriaEntity);
        return modelMapper.map(salvarCategoria, Categoria.class);
    }

    public void excluirCategoria(Long id) {
        if (id != null) {
            catRep.deleteById(id);
        }
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
        Optional<NegocioEntity> negocioEntity = negRep.findById(id);
        if (!negocioEntity.isEmpty()) {
            return modelMapper.map(negocioEntity.get(), Negocio.class);
        }
        return null;
    }

    public Negocio salvarNegocio(Negocio negocio) {
        NegocioEntity negocioEntity = modelMapper.map(negocio, NegocioEntity.class);
        NegocioEntity salvarNegocio = negRep.save(negocioEntity);
        return modelMapper.map(salvarNegocio, Negocio.class);
    }

    public void excluirNegocio(Long id) {
        if (id != null) {
            negRep.deleteById(id);
        }
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
        Optional<QuartoEntity> quartoEntity = quaRep.findById(id);
        if (!quartoEntity.isEmpty()) {
            return modelMapper.map(quartoEntity.get(), Quarto.class);
        }
        return null;
    }

    public Quarto salvarQuarto(Quarto quarto) {
        QuartoEntity quartoEntity = modelMapper.map(quarto, QuartoEntity.class);
        QuartoEntity salvarQuarto = quaRep.save(quartoEntity);
        return modelMapper.map(salvarQuarto, Quarto.class);
    }

    public void excluirQuarto(Long id) {
        if (id != null) {
            quaRep.deleteById(id);
        }
    }

    public List<Quarto> findQuartoByQuantidade(Integer quantidade) {
        List<QuartoEntity> quartos = quaRep.findByQuantidade(quantidade);
        return quartos.stream().map(entity -> modelMapper.map(entity, Quarto.class)).collect(Collectors.toList());
    }

}
