package imobiliaria.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="quartos")
public class Quartos implements Serializable{

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "A quantidade de quartos não pode estar vazia.")
    @DecimalMin(value = "1", message = "O valor não pode ser menor que 1")
    @DecimalMax(value = "10", message = "O valor não pode ser maior que 10")
    private Integer quantidade;
    
	@NotBlank(message = "A Descrição dos quartos não pode estar em branco.")
    private String descricao;

    @OneToMany
    @JoinColumn(name="QuartoImovel")
    private List<Imovel> imoveis;

    
	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
