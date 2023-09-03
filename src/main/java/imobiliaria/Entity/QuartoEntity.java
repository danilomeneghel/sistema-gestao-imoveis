package imobiliaria.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quarto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A quantidade de quartos não pode estar vazia.")
    @DecimalMin(value = "1", message = "O valor não pode ser menor que 1")
    @DecimalMax(value = "10", message = "O valor não pode ser maior que 10")
    private Integer quantidade;

    @NotBlank(message = "A descrição do quarto não pode estar em branco.")
    private String descricao;

}
