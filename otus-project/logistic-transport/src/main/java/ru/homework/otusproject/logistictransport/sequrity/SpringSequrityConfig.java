package ru.homework.otusproject.logistictransport.sequrity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import ru.homework.otusproject.utils.converter.KCRoleConverter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSequrityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KCRoleConverter());

        http
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests.requestMatchers("/add").hasRole("user")
                                             .requestMatchers("/add").hasRole("admin")
                                .requestMatchers("/update/*").hasRole("user")
                                .requestMatchers("/delete/*").hasRole("user")
                                .requestMatchers("/update/*").hasRole("admin")
                                .requestMatchers("/delete/*").hasRole("admin")
                ).authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.anyRequest().authenticated())
                .oauth2ResourceServer(configurer -> configurer
                        .jwt(oauth -> oauth.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );

        return http.build();
    }
}
