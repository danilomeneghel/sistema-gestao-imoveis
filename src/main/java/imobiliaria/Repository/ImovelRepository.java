package imobiliaria.Repository;

import imobiliaria.Entity.ImovelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long>, QueryByExampleExecutor<ImovelEntity> {

}
