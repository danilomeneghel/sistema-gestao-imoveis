package imoveis.repository;

import imoveis.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long> {

    ImovelEntity findByCategoria(CategoriaEntity categoriaEntity);

    ImovelEntity findByNegocio(NegocioEntity negocioEntity);

    ImovelEntity findByQuarto(QuartoEntity quartoEntity);

    ImovelEntity findByBairro(BairroEntity bairroEntity);

}
