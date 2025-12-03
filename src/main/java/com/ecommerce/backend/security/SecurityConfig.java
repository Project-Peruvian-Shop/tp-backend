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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedOrigin("http://localhost:8081");
        configuration.addAllowedOrigin("https://tp-frontend-sooty.vercel.app");
        configuration.addAllowedOrigin("https://www.tuberiasperuanito.com");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests

                        // ---------------------
                        // Endpoints públicos
                        // ---------------------
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/{id}", "/api/v1/producto/paginated", "/api/v1/producto/sugeridos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categoria/all-and-quantity").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/imagen/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/dashboard-search").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/mensaje/reclamaciones").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/mensaje/contactenos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register", "/api/v1/auth/login", "/api/v1/auth/refresh-token").permitAll()
                        .requestMatchers("/uploads/**", "/h2-console/**", "/v3/api-docs/**", "/webjars/swagger-ui/**", "/swagger-ui/**").permitAll()

                        // -------------------------------------------------
                        // Endpoints con rol: CLIENTE, SUPERVISOR, ADMINISTRADOR o SUPERADMIN
                        // -------------------------------------------------
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/cotizacion/{id}",
                                "/api/v1/cotizacion/{id}/historial",
                                "/api/v1/cotizacion/productos-por-cotizacion/{cotizacionId}")
                        .hasAnyRole("CLIENTE", "SUPERVISOR", "ADMINISTRADOR", "SUPERADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/cotizacion/by-usuario/{id}")
                        .hasAnyRole("CLIENTE", "SUPERVISOR", "ADMINISTRADOR", "SUPERADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/cotizacion/by-usuario-paginated/{id}")
                        .hasAnyRole("CLIENTE", "SUPERVISOR", "ADMINISTRADOR", "SUPERADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/usuario/{id}")
                        .hasAnyRole("CLIENTE", "SUPERVISOR", "ADMINISTRADOR", "SUPERADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/create")
                        .hasAnyRole("CLIENTE", "ADMINISTRADOR", "SUPERADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/cotizacion/cotizaciones_year")
                        .hasAnyRole("ADMINISTRADOR", "SUPERVISOR", "SUPERADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/categoria/productos/{id}")
                        .hasAnyRole("CLIENTE", "SUPERVISOR", "ADMINISTRADOR", "SUPERADMIN")


                        // -------------------------------------------------
                        // Endpoints con rol: ADMINISTRADOR, SUPERVISOR o SUPERADMIN
                        // -------------------------------------------------

                        // USUARIO
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/usuario/{id}/workers",
                                "/api/v1/usuario/dashboard-search",
                                "/api/v1/usuario/dashboard-quantity",
                                "/api/v1/usuario/dashboard-paginated")
                        .hasAnyRole("ADMINISTRADOR", "SUPERVISOR", "SUPERADMIN")


                        // PRODUCTO
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/producto/dashboard-quantity",
                                "/api/v1/producto/dashboard-paginated")
                        .hasAnyRole("ADMINISTRADOR", "SUPERVISOR", "SUPERADMIN")

                        // MENSAJE
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/mensaje/{id}",
                                "/api/v1/mensaje/dashboard-search",
                                "/api/v1/mensaje/dashboard-quantity/{mes}",
                                "/api/v1/mensaje/dashboard-paginated")
                        .hasAnyRole("ADMINISTRADOR", "SUPERVISOR", "SUPERADMIN")

                        // COTIZACION
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/cotizacion/mensajes-mes",
                                "/api/v1/cotizacion/last-cotizaciones",
                                "/api/v1/cotizacion/dashboard-search",
                                "/api/v1/cotizacion/dashboard-quantity",
                                "/api/v1/cotizacion/dashboard-paginated")
                        .hasAnyRole("ADMINISTRADOR", "SUPERVISOR", "SUPERADMIN")

                        // CATEGORIA
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/categoria/{id}",
                                "/api/v1/categoria/dashboard-search",
                                "/api/v1/categoria/dashboard-quantity",
                                "/api/v1/categoria/dashboard-paginated")
                        .hasAnyRole("ADMINISTRADOR", "SUPERVISOR", "SUPERADMIN")

                        // DASHBOARD
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/dashboard/resumen-kpis",
                                "/api/v1/dashboard/productos-mas-cotizados",
                                "/api/v1/dashboard/cotizaciones-por-mes",
                                "/api/v1/dashboard/categorias-mas-cotizadas")
                        .hasAnyRole("ADMINISTRADOR", "SUPERVISOR", "SUPERADMIN")

                        // -------------------------------------------------
                        // Endpoints exclusivos del rol SUPERADMIN
                        // -------------------------------------------------

                        // USUARIO
                        .requestMatchers(HttpMethod.POST, "/api/v1/usuario/save").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/usuario/update/{id}").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/usuario/delete/{id}").hasRole("SUPERADMIN")

                        // PRODUCTO
                        .requestMatchers(HttpMethod.POST, "/api/v1/producto/").hasAnyRole("SUPERADMIN", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/producto/{id}").hasAnyRole("SUPERADMIN", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/producto/{id}").hasRole("SUPERADMIN")

                        // MENSAJE
                        .requestMatchers(HttpMethod.PUT, "/api/v1/mensaje/change-state/{id}").hasAnyRole("SUPERADMIN", "ADMINISTRADOR")

                        // COTIZACION
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cotizacion/observaciones/{id}").hasAnyRole("SUPERADMIN", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cotizacion/change-state/{id}")
                        .hasAnyRole("CLIENTE", "SUPERVISOR", "ADMINISTRADOR", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cotizacion/create_pdf/{id}").hasAnyRole("SUPERADMIN", "ADMINISTRADOR")

                        // CATEGORIA
                        .requestMatchers(HttpMethod.POST, "/api/v1/categoria/").hasAnyRole("SUPERADMIN", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/categoria/{id}").hasAnyRole("SUPERADMIN", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/categoria/{id}").hasRole("SUPERADMIN")

                        // IMAGEN
                        .requestMatchers(HttpMethod.POST, "/api/v1/imagen").hasAnyRole("SUPERADMIN", "ADMINISTRADOR")

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
