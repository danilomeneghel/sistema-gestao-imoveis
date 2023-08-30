package imobiliaria.Configuration;

import imobiliaria.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository rep;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //Define os acessos para cada usuário, com apenas a tela de login sendo
        //acessível sem utilizar uma conta
        http.authorizeRequests()
                .antMatchers("/local/**","/classificador/**","/imovel/**","/imagem/**").hasRole("ADMIN")
                .antMatchers("/user/**","/","/ajax").hasAnyRole("USER","ADMIN")
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .and().logout().permitAll();
        //Desativa o csrf para ser possível enviar imagens em forms multpart
        //Tentei seguir o exemplo de como utilizá-lo porém não consegui fazer
        //a configuração csrf funcionar, fazendo que todos os uploads ficassem como
        //'403 - forbidden'
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    public String setEncoder(String password) {
        return encoder.encode(password);
    }

}