package imobiliaria.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "imovel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O valor do imóvel não pode estar em branco.")
    @DecimalMin(value = "0.01", message = "O valor do imóvel não pode ser R$0.00 ou negativo.")
    @DecimalMax(value = "99999999.99", message = "O valor do imóvel não pode ser maior que R$10000000.00")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_negocio")
    @JsonBackReference
    private NegocioEntity negocio;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonBackReference
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    @JsonBackReference
    private QuartoEntity quarto;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    @JsonBackReference
    private BairroEntity bairro;

    @ManyToOne
    @JoinColumn(name = "id_imagem")
    @JsonBackReference
    private ImagemEntity imagem;

}
