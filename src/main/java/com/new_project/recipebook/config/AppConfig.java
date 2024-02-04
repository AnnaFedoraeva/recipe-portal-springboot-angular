package com.new_project.recipebook.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
       // sets the session creation policy to STATELESS.
        //without creation or use HTTP sessions in order to eliminate the risk of session-related vulnerabilities
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                        .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                        .csrf(csrf -> csrf.disable())
                        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                        .formLogin(withDefaults());

        return http.build();
    }



    //Cross-Origin Resource Sharing (CORS) is an HTTP-header based mechanism
    // that allows a server to indicate any other origins (domain, scheme, or port)
    // than its own from which a browser should permit loading of resources.
    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {

            //allowing requests from any origin (*) for development purposes
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Collections.singletonList("*"));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setExposedHeaders(Collections.singletonList("*"));
                cfg.setMaxAge(3600L);
                return cfg;
            }
        };

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
