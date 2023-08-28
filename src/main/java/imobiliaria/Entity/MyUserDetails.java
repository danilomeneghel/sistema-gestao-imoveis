package imobiliaria.Entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Não é uma entidade mas faz o intermédio entre a 
//entidade user e sua utilização em segurança
public class MyUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String email;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;
	
	//Recebe os dados básicos de um usuário e os adapta para o modelo
	//UserDetails utilizado no spring security
	//Originalmente os roles, ou autoridades, são salvos como uma String
	//dividida por vírgulas e o construtor a transforma na lista de 
	//Granted Authority necessária
	public MyUserDetails(User user) {
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}
	
	public String getEmail() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

}
