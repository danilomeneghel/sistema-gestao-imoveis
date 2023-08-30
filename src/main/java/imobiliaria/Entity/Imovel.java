package imobiliaria.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="imovel")
public class Imovel implements Serializable{

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "O valor do imóvel não pode estar em branco.")
    @DecimalMin(value="0.01",message="O valor do imóvel não pode ser R$0.00 ou negativo.")
    @DecimalMax(value="999999.99",message="O valor do imóvel não pode ser maior que R$1000000.00")
    private BigDecimal valor;
    
    @ManyToOne
    @JoinColumn(name="NegocioImovel")
    private Negocio negocio;
    
    @ManyToOne
    @JoinColumn(name="CategoriaImovel")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name="QuartoImovel")
    private Quartos quartos;
    
    @ManyToOne
    @JoinColumn(name="BairroImovel")
    private Bairro bairro;
    
    @OneToMany
    @JoinColumn(name="imagens")
    private List<Imagem> imagens;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Negocio getNegocio() {
		return negocio;
	}
	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Quartos getQuartos() {
		return quartos;
	}
	public void setQuartos(Quartos quartos) {
		this.quartos = quartos;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
    
    public Municipio getMunicipio() {
    	return bairro.getMunicipio();
    }
    
    public Estado getEstado() {
    	return getMunicipio().getEstado();
    }
	@Override
	public String toString() {
		return "Imovel [id=" + id + ", valor=" + valor + ", negocio=" + negocio + ", categoria=" + categoria
				+ ", quartos=" + quartos + ", bairro=" + bairro + "]";
	}
	public List<Imagem> getImagens() {
		return imagens;
	}
	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
    
    
}
