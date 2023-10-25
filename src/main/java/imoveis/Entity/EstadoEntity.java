package imoveis.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "estado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Estado não pode estar em branco.")
    private String nome;

    @NotBlank(message = "A sigla da UF não pode estar em branco.")
    private String uf;

}
