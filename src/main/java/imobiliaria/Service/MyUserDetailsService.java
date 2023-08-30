package imobiliaria.Service;

import imobiliaria.Configuration.WebSecurityConfiguration;
import imobiliaria.Entity.MyUserDetails;
import imobiliaria.Entity.User;
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
        Optional<User> user = rep.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }

    public void saveNewUser(User user) {
        user.setPassword(WebSec.setEncoder(user.getPassword()));
        rep.save(user);
    }

    public boolean emailExistente(String email) {
        Optional<User> user = rep.findByEmail(email);
        return !user.isEmpty();
    }

}
