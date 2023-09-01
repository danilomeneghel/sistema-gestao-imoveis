package imobiliaria.Repository;

import imobiliaria.Entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{
	
	List<CategoriaEntity> findByNomeContainingIgnoreCase(String nome);

}
