package imoveis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imovel {

    private Long id;

    private boolean ativo;

    private BigDecimal valor;

    private Negocio negocio;

    private Categoria categoria;

    private Quarto quarto;

    private Bairro bairro;

    private Municipio municipio;

    private Estado estado;

    private List<Imagem> imagens;

    private MultipartFile[] files;

}
