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
@Table(name = "estado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Estado não pode estar em branco.")
    private String nome;

    @NotBlank(message = "A sigla da UF não pode estar em branco.")
    private String uf;

    @OneToMany
    @JoinColumn(name = "estado_municipio")
    private List<MunicipioEntity> municipios;

}
