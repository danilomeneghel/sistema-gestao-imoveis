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
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private Estado estado;

    private List<Bairro> bairros;

}
