package com.ecommerce.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/api/v1/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    /* --Configuración para todos los endpoints--
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        // Endpoints accessible without authentication
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/{id}", "/api/v1/producto/paginated").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categoria/productos/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/mensaje/reclamaciones").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/mensaje/contactenos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/refresh-token").permitAll()
                        .requestMatchers("/h2-console/**", "/v3/api-docs/**", "/webjars/swagger-ui/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/api/v1/addresses/{countryName}/pickup-sites").permitAll()

                        // Endpoints con rol de :  USER  ADMIN o MANAGER
                        .requestMatchers(HttpMethod.GET, "/api/v1/cotizacion/{id}","/api/v1/cotizacion/{id}/historial","/api/v1/cotizacion/productos-por-cotizacion/{cotizacionId}").hasRole("USER","ADMIN","MANAGER")

                        // Endpoints con rol de : ADMIN o MANAGER

                        // Endpoints con rol de : ADMIN
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
*/


}
