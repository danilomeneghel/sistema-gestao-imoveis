package imobiliaria.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "negocio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NegocioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do negócio não pode estar em branco.")
    private String nome;

    @OneToMany(mappedBy = "negocio", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<ImovelEntity> imoveis;

}
