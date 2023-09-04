package imobiliaria.Repository;

import imobiliaria.Entity.MunicipioEntity;
import imobiliaria.Model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Long> {

    List<MunicipioEntity> findByNomeContainingIgnoreCase(String nome);

    List<MunicipioEntity> findByBairros(Bairro bairro);

}
