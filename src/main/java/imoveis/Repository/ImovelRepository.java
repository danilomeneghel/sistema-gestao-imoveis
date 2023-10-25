package imoveis.Repository;

import imoveis.Entity.CategoriaEntity;
import imoveis.Entity.ImovelEntity;
import imoveis.Entity.NegocioEntity;
import imoveis.Entity.QuartoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long>, QueryByExampleExecutor<ImovelEntity> {

    ImovelEntity findByCategoria(CategoriaEntity categoriaEntity);

    ImovelEntity findByNegocio(NegocioEntity negocioEntity);

    ImovelEntity findByQuarto(QuartoEntity quartoEntity);

    ImovelEntity findByBairro(Long id);

}
