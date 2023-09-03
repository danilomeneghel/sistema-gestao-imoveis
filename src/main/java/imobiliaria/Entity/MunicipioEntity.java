package imobiliaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "municipio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MunicipioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do município não pode estar em branco.")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    @JsonIgnore
    private EstadoEntity estado;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<BairroEntity> bairros;

}
