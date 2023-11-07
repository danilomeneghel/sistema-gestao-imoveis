package imoveis.repository;

import imoveis.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long> {

    List<ImovelEntity> findByAtivoTrue();

    List<ImovelEntity> findByAtivoTrueAndCategoria(CategoriaEntity categoriaEntity);

    List<ImovelEntity> findByCategoria(CategoriaEntity categoriaEntity);

    List<ImovelEntity> findByNegocio(NegocioEntity negocioEntity);

    List<ImovelEntity> findByQuarto(QuartoEntity quartoEntity);

    List<ImovelEntity> findByBairro(BairroEntity bairroEntity);

}
