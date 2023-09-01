package imobiliaria.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imovel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private BigDecimal valor;

    private Negocio negocio;

    private Categoria categoria;

    private Quarto quarto;

    private Bairro bairro;

    private List<Imagem> imagens;

}
