package br.senac.lll.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // --- CORREÇÃO: A ordem dos parâmetros foi ajustada ---
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        response.setStatus(HttpStatus.OK.value()); // Status 200
        response.setContentType("application/json;charset=UTF-8");
        
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("redirectUrl", "/"); // A URL de sucesso que você usava antes

        response.getWriter().write(objectMapper.writeValueAsString(data));
    }
}

