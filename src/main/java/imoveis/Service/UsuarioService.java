package imoveis.Service;

import imoveis.Configuration.UserPasswordEncoder;
import imoveis.Entity.UsuarioEntity;
import imoveis.Model.MyUserDetails;
import imoveis.Model.Usuario;
import imoveis.Repository.UsuarioRepository;
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
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository rep;

    @Autowired
    private UserPasswordEncoder encoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> user = rep.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }

    public List<Usuario> findAllUsuarios() {
        List<UsuarioEntity> usuarios = rep.findAll();
        usuarios.forEach(usuario -> usuario.setPassword(null));
        return usuarios.stream().map(entity -> modelMapper.map(entity, Usuario.class)).collect(Collectors.toList());
    }

    public Usuario findUsuarioById(Long id) {
        Optional<UsuarioEntity> usuario = rep.findById(id);
        usuario.get().setPassword(null);
        return modelMapper.map(usuario.get(), Usuario.class);
    }

    public Usuario findUsuarioByUsername(String username) {
        Optional<UsuarioEntity> usuario = rep.findByUsername(username);
        usuario.get().setPassword(null);
        return modelMapper.map(usuario.get(), Usuario.class);
    }

    public Usuario saveUsuario(Usuario usuario) {
        usuario.setPassword(encoder.setEncoder(usuario.getPassword()));
        UsuarioEntity usuarioEntity = modelMapper.map(usuario, UsuarioEntity.class);
        UsuarioEntity saveUsuario = rep.save(usuarioEntity);
        return modelMapper.map(saveUsuario, Usuario.class);
    }

    public void excluirUsuarioById(Long id) {
        rep.deleteById(id);
    }

    public boolean emailExistente(String email) {
        Optional<UsuarioEntity> usuario = rep.findByEmail(email);
        return !usuario.isEmpty();
    }

}
