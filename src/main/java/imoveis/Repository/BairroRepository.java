package imoveis.Repository;

import imoveis.Entity.BairroEntity;
import imoveis.Entity.MunicipioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BairroRepository extends JpaRepository<BairroEntity, Long> {

    List<BairroEntity> findByNomeContainingIgnoreCase(String nome);

    List<BairroEntity> findByMunicipio(MunicipioEntity municipio);

}
