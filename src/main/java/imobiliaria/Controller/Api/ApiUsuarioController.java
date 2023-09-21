package imobiliaria.Controller.Api;

import imobiliaria.Model.User;
import imobiliaria.Service.MyUserDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuário")
@Validated
public class ApiUsuarioController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(myUserDetailsService.findUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> mostrarUsuarios() {
        return new ResponseEntity<>(myUserDetailsService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<User> cadastroUsuario(@RequestBody User user) {
        return new ResponseEntity<>(myUserDetailsService.saveNewUser(user), HttpStatus.CREATED);
    }

}
