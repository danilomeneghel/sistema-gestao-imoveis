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
public class Bairro implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private Municipio municipio;

    private List<Imovel> imoveis;

}
