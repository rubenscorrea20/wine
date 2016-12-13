package com.wine.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// adicionar um usuario e senha e uma role
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("joao").password("joao").roles("CADASTRAR_VINHO")
			.and()
			.withUser("rubens").password("12345").roles("CADASTRAR_VINHO", "LISTAR_VINHO");
		
	}
	

	// Cofiguração para ignorar o bloqueio de paginas de layout
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**");
	}
	
	// Toda requisição precisa estar autenticado / criando formulario de login / deslogar
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/vinhos/novo").hasRole("CADASTRAR_VINHO")
				.antMatchers("/vinhos/**").hasRole("LISTAR_VINHO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
}
