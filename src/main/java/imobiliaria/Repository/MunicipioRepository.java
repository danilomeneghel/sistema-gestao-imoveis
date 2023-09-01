package imobiliaria.Repository;

import imobiliaria.Entity.MunicipioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Long>{

	public List<MunicipioEntity> findByNomeContainingIgnoreCase(String nome);
}
