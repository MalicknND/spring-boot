package org.msn.produits.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import
        org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/api/all/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/getbyid/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/api/addprod/**").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/updateprod/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/delprod/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated())
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
