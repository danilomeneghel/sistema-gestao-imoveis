package imobiliaria.Repository;

import imobiliaria.Entity.CategoriaEntity;
import imobiliaria.Entity.ImovelEntity;
import imobiliaria.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long>, QueryByExampleExecutor<ImovelEntity> {

    ImovelEntity findByCategoria(CategoriaEntity categoriaEntity);

    ImovelEntity findByNegocio(Long id);

    ImovelEntity findByQuarto(Long id);

    ImovelEntity findByBairro(Long id);

}
