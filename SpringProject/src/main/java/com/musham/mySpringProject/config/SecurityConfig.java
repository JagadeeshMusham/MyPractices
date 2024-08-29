package com.musham.mySpringProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {

/**
 * WebSecurityConfigurerAdapter class is removed in Spring Security 5.7.0
 *
 */
public class SecurityConfig {

    //Authentications
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("Jagadeesh")
                .password(encoder.encode("jagadeesh"))
                .roles("ADMIN").build();

        UserDetails user = User.withUsername("Musham")
                .password(encoder.encode("musham"))
                .roles("USER").build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/journal/**").permitAll()
//                .requestMatchers("/users/**").authenticated()
//                .and().formLogin()
//                .and().build();

        http.authorizeRequests()
                .requestMatchers("/user/**").authenticated()
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
        ; //this is basic and functional**
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
