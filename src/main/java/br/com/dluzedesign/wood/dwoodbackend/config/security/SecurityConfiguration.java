package br.com.dluzedesign.wood.dwoodbackend.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final SecurityFilter securityFilter;
    @Value("${api.prefix}")
    private String apiPrefix;

    private static final String[] PUBLIC_POST = {
            "/auth/*",
            "/newsletter"
    };

    private static final String[] PUBLIC_GET = {
            "/banner/*",
            "/category/**",
            "/products/**"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String[] prefixedPost = addPrefix(apiPrefix, PUBLIC_POST);
        String[] prefixedGet = addPrefix(apiPrefix, PUBLIC_GET);

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers(HttpMethod.POST, prefixedPost).permitAll()
                        .requestMatchers(HttpMethod.GET, prefixedGet).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    private String[] addPrefix(String prefix, String[] paths) {
        if (prefix == null || prefix.isBlank()) return paths;
        return Arrays.stream(paths)
                .map(path -> prefix + (path.startsWith("/") ? path : "/" + path))
                .toArray(String[]::new);
    }
}
