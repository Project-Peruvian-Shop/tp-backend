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

                        // ---------------------
                        // Endpoints públicos
                        // ---------------------
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/{id}", "/api/v1/producto/paginated").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categoria/all-and-quantity").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/imagen/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/mensaje/reclamaciones").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/mensaje/contactenos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register", "/api/v1/auth/login", "/api/v1/auth/refresh-token").permitAll()
                        .requestMatchers("/uploads/**", "/h2-console/**", "/v3/api-docs/**", "/webjars/swagger-ui/**", "/swagger-ui/**").permitAll()

                        // -------------------------------------------------
                        // Endpoints con rol: CLIENTE, ADMINISTRADOR o SUPERADMIN
                        // (antes: USER, MANAGER, ADMIN)
                        // -------------------------------------------------
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/cotizacion/{id}",
                                "/api/v1/cotizacion/{id}/historial",
                                "/api/v1/cotizacion/productos-por-cotizacion/{cotizacionId}")
                        .hasAnyRole("CLIENTE", "ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/cotizacion/by-usuario/{id}").hasAnyRole("CLIENTE", "ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/cotizacion/by-usuario-paginated/{id}").hasAnyRole("CLIENTE", "ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuario/{id}").hasAnyRole("CLIENTE", "ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/create").hasAnyRole("CLIENTE", "ADMINISTRADOR", "SUPERADMIN")

                        // -------------------------------------------------
                        // Endpoints con rol: ADMINISTRADOR o SUPERADMIN
                        // (antes: MANAGER o ADMIN)
                        // -------------------------------------------------

                        // USUARIO
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuario/{id}/workers").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuario/dashboard-search").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuario/dashboard-quantity").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuario/dashboard-paginated").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")

                        // PRODUCTO
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/dashboard-search").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/dashboard-quantity").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/dashboard-paginated").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")

                        // MENSAJE
                        .requestMatchers(HttpMethod.GET, "/api/v1/mensaje/{id}").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/mensaje/dashboard-search").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/mensaje/dashboard-quantity/{mes}").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/mensaje/dashboard-paginated").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")

                        // COTIZACION
                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/mensajes-mes").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/last-cotizaciones").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/dashboard-search").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/dashboard-quantity").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/dashboard-paginated").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")

                        // CATEGORIA
                        .requestMatchers(HttpMethod.GET, "/api/v1/categoria/{id}").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/categoria/dashboard-search").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/categoria/dashboard-quantity").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/categoria/dashboard-paginated").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")

                        // DASHBOARD
                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard/resumen-kpis").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard/productos-mas-cotizados").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard/cotizaciones-por-mes").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard/categorias-mas-cotizadas").hasAnyRole("ADMINISTRADOR", "SUPERADMIN")

                        // -------------------------------------------------
                        // Endpoints exclusivos del rol SUPERADMIN
                        // (antes: ADMIN)
                        // -------------------------------------------------

                        // USUARIO
                        .requestMatchers(HttpMethod.POST, "/api/v1/usuario/save").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/usuario/update/{id}").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/usuario/delete/{id}").hasRole("SUPERADMIN")

                        // PRODUCTO
                        .requestMatchers(HttpMethod.POST, "/api/v1/producto/").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/producto/{id}").hasRole("SUPERADMIN")

                        // MENSAJE
                        .requestMatchers(HttpMethod.PUT, "/api/v1/mensaje/change-state/{id}").hasRole("SUPERADMIN")

                        // COTIZACION
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cotizacion/observaciones/{id}").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cotizacion/change-state/{id}").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/create_pdf/{id}").hasRole("SUPERADMIN")

                        // CATEGORIA
                        .requestMatchers(HttpMethod.POST, "/api/v1/categoria/").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/categoria/{id}").hasRole("SUPERADMIN")

                        // IMAGEN
                        .requestMatchers(HttpMethod.POST, "/api/v1/imagen").hasRole("SUPERADMIN")

                        // -------------------------------------------------
                        // Todo lo demás requiere autenticación
                        // -------------------------------------------------
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
}
