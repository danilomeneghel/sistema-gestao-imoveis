package imobiliaria.Repository;

import imobiliaria.Entity.BairroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BairroRepository extends JpaRepository<BairroEntity, Long>{

	public List<BairroEntity> findByNomeContainingIgnoreCase(String nome);
}
