package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.daos.UsuarioDAO;
import br.com.casadocodigo.loja.services.UsuarioService;

//@EnableWebSecurity
@EnableWebMvcSecurity
public class SpringSecurityConf extends WebSecurityConfigurerAdapter{

	@Autowired private UsuarioService usuarioService;
	
	// o default pede que estaja logado sempre. sobrescrevemos pois temos coisas publicas que nao precisa de login
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		  .antMatchers("/livros").permitAll()
		  .antMatchers("/livros/").permitAll()
		  .antMatchers("/livros/detalhe/**").permitAll()
		  .antMatchers("/livros/form/**").hasRole("ADMIN")
		  .antMatchers("/shopping/**").permitAll()

		  //.antMatchers("resources/**").permitAll()
		  .anyRequest().authenticated()
		  .and()
		  .formLogin()
		  .loginPage("/login")
		  .defaultSuccessUrl("/livros").permitAll()
		  .and()
		  .logout()
		  .logoutSuccessUrl("/login").permitAll()
		  .logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
		  .and()
		  .exceptionHandling().accessDeniedPage("/WEB-INF/views/errors/403.jsp;");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
