package com.example.springthymeleaf.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

// mark this class as configuration class
@Configuration
// enable security
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Inject user service
	@Autowired
	private UserDetailsService userService;
	
	/**
	 * Create DaoAuthenticationProvider as authentication provider
	 * It is used to authenticate when logic. it will be called from authentication filter
	 * @return
	 */
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		// register user service to look up user by name
		// it will call method findByUsername(..) for user service
		authProvider.setUserDetailsService(userService);
		// use plain text for password
		authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		// throw exception when user not found
		authProvider.setHideUserNotFoundExceptions(false);
		return authProvider;
	}
	
	/**
	 * configure role for security
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				// url /resource/** will be allowed
				.antMatchers("/resources/**", "/login").permitAll()
				// other url will be authenticated
				.anyRequest().authenticated()
				.and()
			// use custom login form
			.formLogin()
				// register login url
				.loginPage("/login")
				// define url when login success
				.defaultSuccessUrl("/")
				// define url when login fail
				.failureUrl("/login")
				.and()
			.logout()
				// define logout url
				.logoutSuccessUrl("/login")
				// remove session
				.invalidateHttpSession(true);
		;  
	}
	
	/**
	 * Register authentication provider to spring security
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
}
