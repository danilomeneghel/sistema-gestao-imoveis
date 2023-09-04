package imobiliaria.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

}
