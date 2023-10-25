package imoveis.Repository;

import imoveis.Entity.NegocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NegocioRepository extends JpaRepository<NegocioEntity, Long> {

    List<NegocioEntity> findByNomeContainingIgnoreCase(String nome);

}
