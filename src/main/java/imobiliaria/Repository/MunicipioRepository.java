package imobiliaria.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imobiliaria.Entity.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{

	public Collection<Municipio> findByNomeContainingIgnoreCase(String nome);
}
