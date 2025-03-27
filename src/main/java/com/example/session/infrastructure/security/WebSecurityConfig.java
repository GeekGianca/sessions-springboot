package com.example.session.infrastructure.security;

import com.example.session.core.domain.persistence.IUserRepository;
import com.example.session.core.domain.service.CustomUserDetailService;
import com.example.session.core.domain.service.ISessionService;
import com.example.session.core.usecase.ICreateSessionUseCase;
import com.example.session.infrastructure.config.AuthFailureHandler;
import com.example.session.infrastructure.config.AuthSuccessHandler;
import com.example.session.infrastructure.config.LogoutSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final CustomUserDetailService service;
    private final IUserRepository userRepository;
    private final ICreateSessionUseCase createSessionUseCase;
    private final ISessionService sessionService;

    public WebSecurityConfig(CustomUserDetailService service, IUserRepository userRepository, ICreateSessionUseCase createSessionUseCase, ISessionService sessionService) {
        this.service = service;
        this.userRepository = userRepository;
        this.createSessionUseCase = createSessionUseCase;
        this.sessionService = sessionService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilterAfter(new LoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/user-signup").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/")
                        .loginProcessingUrl("/")
                        .failureUrl("/?error=true")
                        .defaultSuccessUrl("/home", true)
                        .successHandler(new AuthSuccessHandler(createSessionUseCase))
                        .failureHandler(new AuthFailureHandler(userRepository, passwordEncoder()))
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(new LogoutSuccessHandler(sessionService))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(service);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:8080/", "http://127.0.0.1", "127.0.0.1:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/public/**", configuration);

        return source;
    }
}
