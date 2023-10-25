package imoveis.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco.")
    private String name;

    @NotBlank(message = "O usuário não pode estar em branco.")
    private String username;

    @NotBlank(message = "O e-mail não pode estar em branco.")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco.")
    private String password;

    private boolean active;

    private String roles;

}
