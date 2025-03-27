package com.farmersol.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF (for testing, enable in production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Allow all requests
                )
                .formLogin(form -> form.disable())  // Disable the default login form
                .httpBasic(basic -> basic.disable()); // Disable basic authentication

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS requests from localhost:3000 (your React app)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Allow the front-end URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }
}
