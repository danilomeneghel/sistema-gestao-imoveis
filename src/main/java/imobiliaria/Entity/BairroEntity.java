package imobiliaria.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "bairro")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BairroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do bairro não pode estar em branco.")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    @JsonIgnore
    private MunicipioEntity municipio;

    @OneToMany(mappedBy = "bairro", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ImovelEntity> imoveis;

}
