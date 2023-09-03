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
public class Estado {

    private Long id;

    private String nome;

    private String uf;

    private List<Municipio> municipios;

}
