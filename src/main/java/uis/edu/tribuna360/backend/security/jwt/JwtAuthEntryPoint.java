/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.security.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Maneja los errores de autenticación devolviendo un código 401 (Unauthorized)
 * y un mensaje personalizado cuando el usuario no está autenticado o el token es inválido.
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) 
            throws IOException, ServletException {
        // Responde con 401 y un mensaje de error
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: No autorizado o token inválido");
    }
}
