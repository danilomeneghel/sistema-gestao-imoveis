package imobiliaria.Repository;

import imobiliaria.Entity.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ImovelRepository extends JpaRepository<Imovel, Long>,QueryByExampleExecutor<Imovel>{

}
