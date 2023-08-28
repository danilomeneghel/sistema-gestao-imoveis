package imobiliaria.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import imobiliaria.Entity.Quartos;

public interface QuartosRepository extends JpaRepository<Quartos, Long>{

	public Collection<Quartos> findByQuantidade(Integer quantidade);
}
