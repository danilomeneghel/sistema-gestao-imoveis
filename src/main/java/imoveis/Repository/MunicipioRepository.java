package imoveis.Repository;

import imoveis.Entity.EstadoEntity;
import imoveis.Entity.MunicipioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Long> {

    List<MunicipioEntity> findByNomeContainingIgnoreCase(String nome);

    List<MunicipioEntity> findByEstado(EstadoEntity estado);

}
