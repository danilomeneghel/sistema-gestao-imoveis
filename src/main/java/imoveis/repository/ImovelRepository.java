package imoveis.repository;

import imoveis.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long> {

    List<ImovelEntity> findByAtivoTrue();

    ImovelEntity findByCategoria(CategoriaEntity categoriaEntity);

    ImovelEntity findByNegocio(NegocioEntity negocioEntity);

    ImovelEntity findByQuarto(QuartoEntity quartoEntity);

    ImovelEntity findByBairro(BairroEntity bairroEntity);

}
