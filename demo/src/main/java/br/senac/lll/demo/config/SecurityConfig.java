package br.senac.lll.demo.config;

// --- ADIÇÃO: Imports para as novas classes ---
import br.senac.lll.demo.config.CustomAuthenticationFailureHandler;
import br.senac.lll.demo.config.CustomAuthenticationSuccessHandler;
// ---------------------------------------------

import org.springframework.beans.factory.annotation.Autowired;
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

    // --- ADIÇÃO: Injete os novos handlers customizados ---
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;
    // ----------------------------------------------------

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desabilitar CSRF para simplificar o exemplo com AJAX/JSON.
            // Para produção, o ideal é enviar o token CSRF via header no JavaScript.
            .csrf(csrf -> csrf.disable())
            
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'; img-src * data:; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com; font-src 'self' https://fonts.gstatic.com; script-src 'self';")
                )
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/img/**", "/index.css").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") // O JavaScript enviará os dados para esta URL
                
                // --- MUDANÇA: Substitua os redirecionamentos pelos handlers ---
                .successHandler(successHandler) // Usa nosso handler de sucesso
                .failureHandler(failureHandler) // Usa nosso handler de falha
                // .defaultSuccessUrl("/", true)  <-- REMOVIDO
                // .failureUrl("/login?error=true") <-- REMOVIDO
                // -------------------------------------------------------------
                
                .permitAll()
            )
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

