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
                .authorizeHttpRequests(authorize -> authorize
                        // 1. Permite acesso público à página de login e aos arquivos estáticos (CSS,
                        // JS)
                        .requestMatchers("/login", "/register", "/css/**", "/js/**", "/index.css").permitAll()
                        // 2. Exige que QUALQUER OUTRA requisição seja autenticada
                        .anyRequest().authenticated())
                // 3. Configura o formulário de login
                .formLogin(form -> form
                        .loginPage("/login") // Diz ao Spring Security qual é a sua página de login
                        .loginProcessingUrl("/login") // URL que o formulário envia os dados (o Spring Security a
                                                      // gerencia)
                        .defaultSuccessUrl("/", true) // Para onde ir após um login bem-sucedido
                        .failureUrl("/login?error=true") // Para onde ir se o login falhar
                        .permitAll())
                .logout(logout -> logout // Configura a funcionalidade de logout
                        .logoutSuccessUrl("/login?logout=true") // Para onde ir após o logout
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}