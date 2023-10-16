package imobiliaria.Service;

import imobiliaria.Configuration.WebSecurityConfiguration;
import imobiliaria.Entity.UsuarioEntity;
import imobiliaria.Model.MyUserDetails;
import imobiliaria.Model.Usuario;
import imobiliaria.Repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository rep;

    @Autowired
    private WebSecurityConfiguration webSec;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> user = rep.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }

    public List<Usuario> findAllUsers() {
        List<UsuarioEntity> users = rep.findAll();
        users.forEach(user -> user.setPassword(null));
        return users.stream().map(entity -> modelMapper.map(entity, Usuario.class)).collect(Collectors.toList());
    }

    public Usuario findUserByUsername(String username) {
        UsuarioEntity user = rep.findByUsername(username).get();
        user.setPassword(null);
        return modelMapper.map(user, Usuario.class);
    }

    public Usuario saveNewUser(Usuario usuario) {
        usuario.setPassword(webSec.setEncoder(usuario.getPassword()));
        UsuarioEntity usuarioEntity = modelMapper.map(usuario, UsuarioEntity.class);
        UsuarioEntity saveUser = rep.save(usuarioEntity);
        return modelMapper.map(saveUser, Usuario.class);
    }

    public boolean emailExistente(String email) {
        Optional<UsuarioEntity> user = rep.findByEmail(email);
        return !user.isEmpty();
    }

}
