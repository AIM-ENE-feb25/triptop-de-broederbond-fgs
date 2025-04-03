package nl.han.soex.prototype.identityprovider.config;

import nl.han.soex.prototype.identityprovider.security.AuthenticationEntryPointImpl;
import nl.han.soex.prototype.identityprovider.filters.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final AuthenticationFilter authenticationFilter;


    public SecurityConfig(AuthenticationEntryPointImpl authenticationEntryPoint, AuthenticationFilter authenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // TODO: voor prototypes toegang tot alles behalve de protected endpoint.
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/protected").authenticated()
                        .anyRequest().permitAll()
                ).csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }
}