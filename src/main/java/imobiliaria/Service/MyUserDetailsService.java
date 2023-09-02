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

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository rep;

    @Autowired
    private WebSecurityConfiguration webSec;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = rep.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
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
