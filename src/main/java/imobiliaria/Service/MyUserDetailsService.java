package imobiliaria.Service;

import imobiliaria.Configuration.WebSecurityConfiguration;
import imobiliaria.Entity.UserEntity;
import imobiliaria.Model.MyUserDetails;
import imobiliaria.Model.User;
import imobiliaria.Repository.UserRepository;
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
    private UserRepository rep;

    @Autowired
    private WebSecurityConfiguration webSec;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = rep.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }

    public List<User> findAllUsers() {
        List<UserEntity> users = rep.findAll();
        users.forEach(user -> user.setPassword(null));
        return users.stream().map(entity -> modelMapper.map(entity, User.class)).collect(Collectors.toList());
    }

    public User findUserByUsername(String username) {
        UserEntity user = rep.findByUsername(username).get();
        user.setPassword(null);
        return modelMapper.map(user, User.class);
    }

    public User saveNewUser(User user) {
        user.setPassword(webSec.setEncoder(user.getPassword()));
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity saveUser = rep.save(userEntity);
        return modelMapper.map(saveUser, User.class);
    }

    public boolean emailExistente(String email) {
        Optional<UserEntity> user = rep.findByEmail(email);
        return !user.isEmpty();
    }

}
