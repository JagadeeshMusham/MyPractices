package com.musham.mySpringProject.config;

import com.musham.mySpringProject.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {

/**
 * WebSecurityConfigurerAdapter class is removed in Spring Security 5.7.0
 *
 */
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //Authentications
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        List<UserDetails> userDetailsList = new ArrayList<>();

        UserDetails admin = User.withUsername("Jagadeesh")
                .password(encoder.encode("jagadeesh"))
                .roles("ADMIN").build();
        userDetailsList.add(admin);

        UserDetails user = User.withUsername("Musham")
                .password(encoder.encode("musham"))
                .roles("USER").build();
        userDetailsList.add(user);

        List<com.musham.mySpringProject.entity.User> dbUsers = userDetailsService.findAll();
        for (com.musham.mySpringProject.entity.User dbUser : dbUsers) {
            UserDetails userDetails = User.withUsername(dbUser.getUserName())
                    .password(encoder.encode(dbUser.getPassword()))
//                    .roles(dbUser.getRoles()).build();
                    .roles("ADMIN").build();
            userDetailsList.add(userDetails);
        }

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /**
         *  Below code deprecated long back
         */
//                return http.csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/journal/**").permitAll()
//                .requestMatchers("/users/**").authenticated()
//                .and().formLogin()
//                .and().build();

        /**
         *  Below code deprecated very recent time
         */
//        http.authorizeRequests()
//                .requestMatchers("/user/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .httpBasic(Customizer.withDefaults())
////                .csrf(csrf -> csrf.disable()) //CSRF
//
//        /**
//         * disabling CSRF protection is generally not recommended as it significantly
//         * increases the risk of security vulnerabilities. If you must disable it,
//         * ensure that you have a solid understanding of the risks involved and have
//         * implemented appropriate alternative security measures.
//         */
//        ; //this is basic and functional**

        http.authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
//                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll()
        );

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
