package imoveis.repository;

import imoveis.entity.CategoriaEntity;
import imoveis.entity.ImovelEntity;
import imoveis.entity.NegocioEntity;
import imoveis.entity.QuartoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long>, QueryByExampleExecutor<ImovelEntity> {

    ImovelEntity findByCategoria(CategoriaEntity categoriaEntity);

    ImovelEntity findByNegocio(NegocioEntity negocioEntity);

    ImovelEntity findByQuarto(QuartoEntity quartoEntity);

    ImovelEntity findByBairro(Long id);

}
