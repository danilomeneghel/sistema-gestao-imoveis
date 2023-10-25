package imoveis.Repository;

import imoveis.Entity.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Long> {

    List<EstadoEntity> findByNomeContainingIgnoreCase(String nome);

}
