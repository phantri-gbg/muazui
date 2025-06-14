package com.vinnet;

import com.vinnet.model.User;
import com.vinnet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            User user = userService.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().toUpperCase())
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/security/login/**", "/security/register/**", "/css/**", "/js/**", "/images/**", "/", "/home", "/products/**", "/categories").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/seller/**").hasRole("USER") // Người bán phải là User với IsSeller = true
                        .requestMatchers("/user/**", "/cart/**", "/order/**", "/favorites/**", "/messages/**", "/notifications/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/security/login/form")
                        .loginProcessingUrl("/security/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/security/login/error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/security/logout")
                        .logoutSuccessUrl("/home?logout=true")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/security/unauthorized")
                );

        return http.build();
    }
}
