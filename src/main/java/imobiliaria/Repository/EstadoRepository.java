package imobiliaria.Repository;

import imobiliaria.Entity.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Long>{

	public List<EstadoEntity> findByNomeContainingIgnoreCase(String nome);
}
