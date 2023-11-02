package imoveis.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
@Data
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
