package com.okntscgl.Blog.security;

import com.okntscgl.Blog.controller.LogoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final LogoutHandler logoutHandler;

    public SecurityConfig(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/api/auth/login", "/api/auth/register").permitAll()
                        .anyRequest().authenticated())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessUrl("/")
                        .permitAll())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll())
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .permitAll())
                .build();
    }
}
