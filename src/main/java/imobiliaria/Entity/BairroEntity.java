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
@Table(name = "bairro")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BairroEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do bairroEntity não pode estar em branco.")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "municipio")
    private MunicipioEntity municipio;

    @OneToMany
    @JoinColumn(name = "bairro_imovel")
    private List<ImovelEntity> imoveis;

}
