package com.ciena.secureapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());		//This is for no password encryption i.e., plain passwords

//		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

		return authProvider;
	}

//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		List<UserDetails> userDetails = new ArrayList<>();
//		
//		userDetails.add(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER").build());
//
//		return new InMemoryUserDetailsManager(userDetails);
//	}

}
