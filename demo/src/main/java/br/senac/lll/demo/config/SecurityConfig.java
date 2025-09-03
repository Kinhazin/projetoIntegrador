package br.senac.lll.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'; img-src * data:; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com; font-src 'self' https://fonts.gstatic.com; script-src 'self';")
                )
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/img/**", "/index.css").permitAll()
                // Exige que QUALQUER OUTRA requisição seja autenticada
                .anyRequest().authenticated())
            // Configura o formulário de login
            .formLogin(form -> form
                .loginPage("/login") 
                .loginProcessingUrl("/login") 
                .defaultSuccessUrl("/", true) 
                .failureUrl("/login?error=true")
                .permitAll())
            .logout(logout -> logout 
                .logoutSuccessUrl("/login?logout=true") 
                .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}