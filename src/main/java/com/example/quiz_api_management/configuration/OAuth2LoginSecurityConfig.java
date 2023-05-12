package com.example.quiz_api_management.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// A marker annotation - Using this for exposing SecurityFilterChain
@EnableWebSecurity
// This annotation enables annotation @PreAuthorize, which will help us in setting Authorization in roles of User.
@EnableMethodSecurity(securedEnabled = true)
//Without adding this annotation, it cannot permit all some endpoints
@Configuration
// This annotation is recommended by using multiple @Configuration annotations
@Import(QuizManagementConfiguration.class)
// This class is configuration for OAuth2.0 with Google provider
public class OAuth2LoginSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .shouldFilterAllDispatcherTypes(false) // Sets whether all dispatcher types should be filtered
                                .requestMatchers("/api/v1/signup").permitAll() // Only register API does not require authentication and authorization
                                .anyRequest().authenticated()
                                )
                .csrf() // Cross-site Request Forgery
                .disable() // Due to serve browser clients, this option have to be disabled
                .oauth2Login(oauth2login -> oauth2login
                                .loginPage("/api/v1/oauth2/login")
                                .successHandler((request, response, authentication) -> {
                                    if (authentication.isAuthenticated())
                                        response.sendRedirect("/api/v1/quizzes");
                                })
                        )
                .logout(
                       logout -> logout
                               .logoutUrl("api/v1/logout")
                               .logoutSuccessUrl("/api/v1/oauth2/login")
                               )
                .getOrBuild();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


