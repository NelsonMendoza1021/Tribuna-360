/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uis.edu.tribuna360.backend.model.Usuario;

import java.security.Key;
import java.util.Date;

/**
 * Utilidad para la generación, validación y obtención de información desde tokens JWT.
 */
@Component
public class JwtUtils {

    // Clave secreta para firmar el JWT, se configura en application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    // Tiempo de expiración del token JWT (en milisegundos), se configura en application.properties
    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    /**
     * Devuelve la clave de firma a partir del secreto configurado.
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /**
     * Genera un token JWT a partir de los datos del usuario autenticado.
     * 
     * @param authentication Objeto de autenticación de Spring Security.
     * @return Token JWT como String.
     */
    public String generateJwtToken(Authentication authentication) {
        // Obtenemos el usuario autenticado (principal)
        Usuario usuarioPrincipal = (Usuario) authentication.getPrincipal();

        // Creamos el token con información relevante del usuario (claims personalizados)
        return Jwts.builder()
                .setSubject(usuarioPrincipal.getEmail()) // Usamos el email como identificador principal
                .claim("idUsuario", usuarioPrincipal.getIdUsuario())
                .claim("nombre", usuarioPrincipal.getNombre())
                .claim("rol", usuarioPrincipal.getRol())
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Fecha de expiración
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Firma del token
                .compact();
    }

    /**
     * Obtiene el email (subject) a partir de un token JWT.
     * 
     * @param token Token JWT recibido.
     * @return Email del usuario.
     */
    public String getEmailFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Valida si el token JWT recibido es correcto y no ha expirado.
     * 
     * @param authToken Token JWT a validar.
     * @return true si es válido, false si es inválido o ha expirado.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            System.err.println("Firma JWT inválida: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Token JWT mal formado: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("Token JWT expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("Token JWT no soportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Claims del JWT vacíos: " + e.getMessage());
        }
        return false;
    }
}