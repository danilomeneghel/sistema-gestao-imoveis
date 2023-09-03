package imobiliaria.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imovel {

    private Long id;

    private BigDecimal valor;

    private Negocio negocio;

    private Categoria categoria;

    private Quarto quarto;

    private Bairro bairro;

    private Imagem imagem;

}
