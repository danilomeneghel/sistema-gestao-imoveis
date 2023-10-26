package imoveis.repository;

import imoveis.entity.BairroEntity;
import imoveis.entity.MunicipioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BairroRepository extends JpaRepository<BairroEntity, Long> {

    List<BairroEntity> findByNomeContainingIgnoreCase(String nome);

    List<BairroEntity> findByMunicipio(MunicipioEntity municipio);

}
