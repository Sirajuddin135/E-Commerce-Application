package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.JWTFilter;
import com.app.services.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTFilter jwtFilter;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers(AppConstants.PUBLIC_URLS).permitAll()
			.requestMatchers(HttpMethod.GET, AppConstants.USER_URLS).hasAnyRole("USER", "ADMIN")
			.requestMatchers(HttpMethod.POST, AppConstants.USER_URLS).hasAnyRole("USER", "ADMIN")
			.requestMatchers(HttpMethod.PUT, AppConstants.USER_URLS).hasAnyRole("USER", "ADMIN")
			.requestMatchers(HttpMethod.DELETE, AppConstants.USER_URLS).hasAnyRole("USER", "ADMIN")
			.requestMatchers(HttpMethod.GET, AppConstants.ADMIN_URLS).hasRole("ADMIN")
			.requestMatchers(HttpMethod.POST, AppConstants.ADMIN_URLS).hasRole("ADMIN")
			.requestMatchers(HttpMethod.PUT, AppConstants.ADMIN_URLS).hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, AppConstants.ADMIN_URLS).hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(
					(request, response, authException) -> 
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.authenticationProvider(daoAuthenticationProvider());
		
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
		
		return defaultSecurityFilterChain;
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}