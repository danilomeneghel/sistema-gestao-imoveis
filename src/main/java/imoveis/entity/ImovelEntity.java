package imoveis.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "imovel")
@Data
public class ImovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean ativo;

    @NotNull(message = "O valor do imóvel não pode estar em branco.")
    @DecimalMin(value = "0.01", message = "O valor do imóvel não pode ser R$0.00 ou negativo.")
    @DecimalMax(value = "99999999.99", message = "O valor do imóvel não pode ser maior que R$10000000.00")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_negocio")
    private NegocioEntity negocio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quarto")
    private QuartoEntity quarto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bairro")
    private BairroEntity bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio")
    private MunicipioEntity municipio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    private EstadoEntity estado;

    @OneToMany(mappedBy = "imovel", cascade = CascadeType.REMOVE)
    private List<ImagemEntity> imagens;

}
