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
@Table(name = "municipio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MunicipioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do município não pode estar em branco.")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoEntity estado;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.REMOVE)
    private List<BairroEntity> bairros;

}
