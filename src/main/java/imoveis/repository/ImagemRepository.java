package imoveis.repository;

import imoveis.entity.ImagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<ImagemEntity, Long> {

}
