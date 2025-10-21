package com.ecommerce.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests

                        // Endpoints públicos
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/{id}", "/api/v1/producto/paginated").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/imagen/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/mensaje/reclamaciones").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/mensaje/contactenos").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/refresh-token").permitAll()

                        .requestMatchers("/h2-console/**", "/v3/api-docs/**", "/webjars/swagger-ui/**"  , "/swagger-ui/**").permitAll()

                        // Endpoints con rol de :  USER  ADMIN o MANAGER
                        .requestMatchers(HttpMethod.GET, "/api/v1/cotizacion/{id}","/api/v1/cotizacion/{id}/historial","/api/v1/cotizacion/productos-por-cotizacion/{cotizacionId}").hasAnyRole("USER", "ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST , "/api/v1/cotizacion/by-usuario/{id}").hasAnyRole("USER", "ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST , "/api/v1/cotizacion/by-usuario-paginated/{id}").hasAnyRole("USER", "ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/usuario/{id}").hasAnyRole("USER", "ADMIN", "MANAGER")


                        // Endpoints con rol de : ADMIN o MANAGER

                        //Endpoint USUARIO
                        .requestMatchers(HttpMethod.GET , "/api/v1/usuario/{id}/workers").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/usuario/dashboard-search").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/usuario/dashboard-quantity").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/usuario/dashboard-paginated").hasAnyRole("ADMIN", "MANAGER")

                        //Endpoint PRODUCTO
                        .requestMatchers(HttpMethod.GET , "/api/v1/producto/{id}").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/producto/dashboard-search").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/producto/dashboard-quantity").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/producto/dashboard-paginated").hasAnyRole("ADMIN", "MANAGER")

                        //Endpoint Mensaje
                        .requestMatchers(HttpMethod.GET , "/api/v1/mensaje/{id}").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/mensaje/dashboard-search").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/mensaje/dashboard-quantity/{mes}").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/mensaje/dashboard-paginated").hasAnyRole("ADMIN", "MANAGER")

                        //Endpoint COTIZACION
                        .requestMatchers(HttpMethod.POST , "/api/v1/cotizacion/mensajes-mes").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST , "/api/v1/cotizacion/last-cotizaciones").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST , "/api/v1/cotizacion/dashboard-search").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST , "/api/v1/cotizacion/dashboard-quantity").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST , "/api/v1/cotizacion/dashboard-paginated").hasAnyRole("ADMIN", "MANAGER")

                        //Endpoint CATEGORIA
                        .requestMatchers(HttpMethod.GET , "/api/v1/categoria/{id}").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/categoria/dashboard-search").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/categoria/dashboard-quantity").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/categoria/dashboard-paginated").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/categoria/all-and-quantity").hasAnyRole("ADMIN", "MANAGER")

                        //Endpoint DASHBOARD
                        .requestMatchers(HttpMethod.GET , "/api/v1/dashboard/resumen-kpis").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/dashboard/productos-mas-cotizados").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/dashboard/cotizaciones-por-mes").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET , "/api/v1/dashboard/categorias-mas-cotizadas").hasAnyRole("ADMIN", "MANAGER")


                        // Endpoints con rol de : ADMIN

                        //Endpoint USUARIO
                        .requestMatchers(HttpMethod.POST , "/api/v1/usuario/save").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT , "/api/v1/usuario/update/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/api/v1/usuario/delete/{id}").hasRole("ADMIN")

                        //Endpoint PRODUCTO
                        .requestMatchers(HttpMethod.POST , "/api/v1/producto/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT , "/api/v1/producto/{id}").hasRole("ADMIN")

                        //Endpoint MENSAJE
                        .requestMatchers(HttpMethod.PUT , "/api/v1/mensaje/change-state/{id}").hasRole("ADMIN")

                        //Endpoint COTIZACION
                        .requestMatchers(HttpMethod.PUT , "/api/v1/cotizacion/observaciones/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT , "/api/v1/cotizacion/change-state/{id}").hasRole("ADMIN")

                        //Endpoint CATEGORIA
                        .requestMatchers(HttpMethod.POST , "/api/v1/categoria/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT , "/api/v1/categoria/{id}").hasRole("ADMIN")

                        //Endpoint IMAGEN
                        .requestMatchers(HttpMethod.POST , "/api/v1/imagen").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

}
