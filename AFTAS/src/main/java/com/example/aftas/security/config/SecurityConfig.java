package com.example.aftas.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.aftas.shared.Enum.Permission.COMPETITION_MANAGE;
import static com.example.aftas.shared.Enum.Permission.COMPETITION_READ;
import static com.example.aftas.shared.Enum.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers("/api/v1/competitions/**").hasAnyRole(ADHERENT.name(), JURY.name(), MANAGER.name())
                        .requestMatchers(GET, "/api/v1/competitions/**").hasAnyAuthority(COMPETITION_READ.name())
                        .requestMatchers(POST, "/api/v1/competitions/**").hasAnyAuthority(COMPETITION_MANAGE.name())
                        .requestMatchers(DELETE, "/api/v1/competitions/**").hasAnyAuthority(COMPETITION_MANAGE.name())
                        .requestMatchers(PUT, "/api/v1/competitions/**").hasAnyAuthority(COMPETITION_MANAGE.name())
                        .anyRequest()
                        .permitAll())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) ->
                                SecurityContextHolder.clearContext()));
        return httpSecurity.build();
    }
}
