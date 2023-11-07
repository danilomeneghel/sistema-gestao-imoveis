package imoveis.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quarto")
@Data
public class QuartoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A quantidade de quartos não pode estar vazia.")
    private Integer quantidade;

    @NotBlank(message = "A descrição do quarto não pode estar em branco.")
    private String descricao;

}
