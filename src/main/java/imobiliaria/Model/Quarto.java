package imobiliaria.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quarto {

    private Long id;

    private Integer quantidade;

    private String descricao;

    private List<Imovel> imoveis;

}
