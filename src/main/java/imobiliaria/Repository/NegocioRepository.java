package imobiliaria.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import imobiliaria.Entity.Negocio;

public interface NegocioRepository extends JpaRepository<Negocio, Long>{

	public Collection<Negocio> findByNomeContainingIgnoreCase(String nome);
}
