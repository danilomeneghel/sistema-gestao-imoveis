package imobiliaria.Repository;

import imobiliaria.Entity.CategoriaEntity;
import imobiliaria.Entity.ImovelEntity;
import imobiliaria.Entity.NegocioEntity;
import imobiliaria.Entity.QuartoEntity;
import imobiliaria.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long>, QueryByExampleExecutor<ImovelEntity> {

    ImovelEntity findByCategoria(CategoriaEntity categoriaEntity);

    ImovelEntity findByNegocio(NegocioEntity negocioEntity);

    ImovelEntity findByQuarto(QuartoEntity quartoEntity);

    ImovelEntity findByBairro(Long id);

}
