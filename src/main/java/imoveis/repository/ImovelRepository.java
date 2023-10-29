package imoveis.repository;

import imoveis.entity.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImovelRepository extends JpaRepository<ImovelEntity, Long> {

    @Cacheable("imovel")
    List<ImovelEntity> findAll();

    @Cacheable("categoria")
    ImovelEntity findByCategoria(CategoriaEntity categoriaEntity);

    @Cacheable("negocio")
    ImovelEntity findByNegocio(NegocioEntity negocioEntity);

    @Cacheable("quarto")
    ImovelEntity findByQuarto(QuartoEntity quartoEntity);

    @Cacheable("bairro")
    ImovelEntity findByBairro(BairroEntity bairroEntity);

}
