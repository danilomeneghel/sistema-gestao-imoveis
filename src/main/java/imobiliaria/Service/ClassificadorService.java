package imobiliaria.Service;

import java.util.Collection;

import imobiliaria.Entity.Categoria;
import imobiliaria.Entity.Quartos;
import imobiliaria.Repository.CategoriaRepository;
import imobiliaria.Repository.NegocioRepository;
import imobiliaria.Repository.QuartosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imobiliaria.Entity.Negocio;

@Service
public class ClassificadorService {
	
	@Autowired
	private CategoriaRepository catRep;
	
	@Autowired
	private NegocioRepository negRep;
	
	@Autowired
	private QuartosRepository quaRep;
	
	public Collection<Categoria> findAllCategoria() {
		return catRep.findAll();
	}
	
	public Categoria findCategoriaById(Long id) {
		return catRep.findById(id).get();
	}
	
	public void saveCategoria(Categoria categoria) {
		catRep.save(categoria);
	}
	
	public void excluirCategoriaById(Long id) {
		catRep.deleteById(id);
	}
	
	public Collection<Categoria> findCategoriaByNome(String nome){
		return catRep.findByNomeContainingIgnoreCase(nome);
	}
	
	public Collection<Negocio> findAllNegocio() {
		return negRep.findAll();
	}
	
	public Negocio findNegocioById(Long id) {
		return negRep.findById(id).get();
	}
	
	public void saveNegocio(Negocio negocio) {
		negRep.save(negocio);
	}
	
	public void excluirNegocioById(Long id) {
		negRep.deleteById(id);
	}
	
	public Collection<Negocio> findNegocioByNome(String nome){
		return negRep.findByNomeContainingIgnoreCase(nome);
	}
	
	public Collection<Quartos> findAllQuartos() {
		return quaRep.findAll();
	}
	
	public Quartos findQuartosById(Long id) {
		return quaRep.findById(id).get();
	}
	
	public void saveQuartos(Quartos quarto) {
		quaRep.save(quarto);
	}
	
	public void excluirQuartoById(Long id) {
		quaRep.deleteById(id);
	}
	
	public Collection<Quartos> findQuartoByQuantidade(Integer quantidade){
		return quaRep.findByQuantidade(quantidade);
	}
}
