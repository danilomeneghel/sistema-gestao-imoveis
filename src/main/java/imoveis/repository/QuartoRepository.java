package imoveis.repository;

import imoveis.entity.QuartoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuartoRepository extends JpaRepository<QuartoEntity, Long> {

    List<QuartoEntity> findByQuantidade(Integer quantidade);

}
