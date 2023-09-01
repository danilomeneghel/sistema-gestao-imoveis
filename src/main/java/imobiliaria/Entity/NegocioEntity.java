package imobiliaria.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "negocio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NegocioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Negócio não pode estar em branco.")
    private String nome;

    @OneToMany
    @JoinColumn(name = "NegocioImovel")
    private List<ImovelEntity> imoveis;

}
