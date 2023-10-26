package imoveis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

}
