package imobiliaria.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bairro {

    private Long id;

    private String nome;

    private Municipio municipio;

    private List<Imovel> imoveis;

}
