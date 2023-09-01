package imobiliaria.Service;

import imobiliaria.Configuration.WebSecurityConfiguration;
import imobiliaria.Entity.UserEntity;
import imobiliaria.Model.MyUserDetails;
import imobiliaria.Repository.UserRepository;
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
    private WebSecurityConfiguration WebSec;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = rep.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }

    public void saveNewUser(UserEntity userEntity) {
        userEntity.setPassword(WebSec.setEncoder(userEntity.getPassword()));
        rep.save(userEntity);
    }

    public boolean emailExistente(String email) {
        Optional<UserEntity> user = rep.findByEmail(email);
        return !user.isEmpty();
    }

}
