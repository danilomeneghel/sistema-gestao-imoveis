package imobiliaria.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "imovel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImovelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O valor do imóvel não pode estar em branco.")
    @DecimalMin(value = "0.01", message = "O valor do imóvel não pode ser R$0.00 ou negativo.")
    @DecimalMax(value = "999999.99", message = "O valor do imóvel não pode ser maior que R$1000000.00")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "NegocioImovel")
    private NegocioEntity negocio;

    @ManyToOne
    @JoinColumn(name = "categoria_imovel")
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "QuartoImovel")
    private QuartoEntity quarto;

    @ManyToOne
    @JoinColumn(name = "bairro_imovel")
    private BairroEntity bairro;

    @OneToMany
    @JoinColumn(name = "imagens")
    private List<ImagemEntity> imagens;

}
