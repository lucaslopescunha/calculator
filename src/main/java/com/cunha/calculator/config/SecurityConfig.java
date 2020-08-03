package com.cunha.calculator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cunha.calculator.security.jwt.JwtConfigurer;
import com.cunha.calculator.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().
		csrf().disable().
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().
				authorizeRequests()
				.antMatchers("/auth/signin","swagger-ui.html**").permitAll()
				.antMatchers("/pessoa/**").authenticated() 
				.antMatchers("/greeting").denyAll() 
		.and() 
		.apply(new JwtConfigurer(jwtTokenProvider));
	}
}
