package com.security.config;

import com.security.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("=== DÉBUT CONFIGURATION SÉCURITÉ ===");

        http
                .csrf(AbstractHttpConfigurer::disable)

                // FR: LIER LE SERVICE UTILISATEUR PERSONNALISÉ - C'EST LA LIGNE IMPORTANTE !
                // EN: BIND THE CUSTOM USER DETAILS SERVICE - THIS IS THE IMPORTANT LINE!
                .userDetailsService(customUserDetailsService)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/test/**").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        log.info("=== CONFIGURATION SÉCURITÉ TERMINÉE ===");
        log.info("Service utilisateur lié: {}", customUserDetailsService.getClass().getSimpleName());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("=== ENCODEUR: NoOpPasswordEncoder ===");
        return NoOpPasswordEncoder.getInstance();
    }
}