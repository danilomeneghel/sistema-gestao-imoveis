package imoveis.service;

import imoveis.entity.UsuarioEntity;
import imoveis.model.Usuario;
import imoveis.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository rep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Usuario> findAllUsuarios() {
        List<UsuarioEntity> usuarios = rep.findAll();
        usuarios.forEach(usuario -> usuario.setPassword(null));
        return usuarios.stream().map(entity -> modelMapper.map(entity, Usuario.class)).collect(Collectors.toList());
    }

    public Usuario findUsuarioById(Long id) {
        Optional<UsuarioEntity> usuario = rep.findById(id);
        if(usuario.get() != null) {
            usuario.get().setPassword(null);
            return modelMapper.map(usuario.get(), Usuario.class);
        }
        return null;
    }

    public Usuario findUsuarioByUsername(String username) {
        Optional<UsuarioEntity> usuario = rep.findByUsername(username);
        if(usuario.get() != null) {
            usuario.get().setPassword(null);
            return modelMapper.map(usuario.get(), Usuario.class);
        }
        return null;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        UsuarioEntity usuarioEntity = modelMapper.map(usuario, UsuarioEntity.class);
        UsuarioEntity salvarUsuario = rep.save(usuarioEntity);
        return modelMapper.map(salvarUsuario, Usuario.class);
    }

    public void excluirUsuarioById(Long id) {
        if (id != null) {
            rep.deleteById(id);
        }
    }

    public boolean emailExistente(String email) {
        Optional<UsuarioEntity> usuario = rep.findByEmail(email);
        return !usuario.isEmpty();
    }

}
