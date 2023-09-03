package imobiliaria.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "imagem")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String path;

    @ManyToOne
    @JoinColumn(name = "id_imovel")
    @JsonBackReference
    private ImovelEntity imovel;

}
