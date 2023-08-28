package imobiliaria.Service;

import java.util.Optional;

import imobiliaria.Entity.MyUserDetails;
import imobiliaria.Entity.User;
import imobiliaria.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository rep;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = rep.findByUserName(username);
		user.orElseThrow(()->new UsernameNotFoundException("Not found: " + username));
		return user.map(MyUserDetails::new).get();
	}

	public void saveNewUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		rep.save(user);
	}
	
	public boolean existeUserName(String username) {
		Optional<User> user = rep.findByUserName(username);
		return user.isEmpty();
	}
	
	public boolean emailExistente(String email) {
		Optional<User> user = rep.findByEmail(email);
		return !user.isEmpty();
	}
}
