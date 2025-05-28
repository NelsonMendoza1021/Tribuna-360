/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.security.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uis.edu.tribuna360.backend.model.Usuario;
import uis.edu.tribuna360.backend.repository.UsuarioRepository;
import uis.edu.tribuna360.backend.security.jwt.JwtUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    // ======= LOGIN =======
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(loginRequest.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "message", "Credenciales inválidas: usuario o contraseña incorrectos."
            ));
        }

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getContrasena()
                    )
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "message", "Credenciales inválidas: usuario o contraseña incorrectos."
            ));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("token", jwt);
        response.put("usuario", Map.of(
            "id", usuario.getIdUsuario(),
            "nombre", usuario.getNombre(),
            "email", usuario.getEmail(),
            "telefono", usuario.getTelefono(),
            "cedula", usuario.getCedula(),
            "rol", usuario.getRol(),
            "fechaRegistro", usuario.getFechaRegistro()
        ));
        return ResponseEntity.ok(response);
    }

    // ======= REGISTRO =======
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Validar duplicados por email y cédula
        if (usuarioRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "field", "email",
                "message", "El email ya está registrado."
            ));
        }
        if (usuarioRepository.findByCedula(registerRequest.getCedula()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "field", "cedula",
                "message", "La cédula ya está registrada."
            ));
        }

        Usuario usuario = Usuario.builder()
                .nombre(registerRequest.getNombre())
                .email(registerRequest.getEmail())
                .contrasena(passwordEncoder.encode(registerRequest.getContrasena()))
                .telefono(registerRequest.getTelefono())
                .cedula(registerRequest.getCedula())
                .rol("USER")
                .fechaRegistro(LocalDate.now())
                .build();

        usuarioRepository.save(usuario);

        // Si quieres autenticar automáticamente al usuario recién registrado y devolver JWT:
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                registerRequest.getEmail(),
                registerRequest.getContrasena()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("token", jwt);
        response.put("usuario", Map.of(
            "id", usuario.getIdUsuario(),
            "nombre", usuario.getNombre(),
            "email", usuario.getEmail(),
            "telefono", usuario.getTelefono(),
            "cedula", usuario.getCedula(),
            "rol", usuario.getRol(),
            "fechaRegistro", usuario.getFechaRegistro()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ======= DTOs =======
    public static class LoginRequest {
        private String email;
        private String contrasena;

        // Getters y Setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getContrasena() { return contrasena; }
        public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    }

    public static class RegisterRequest {
        private String nombre;
        private String email;
        private String contrasena;
        private String telefono;
        private String cedula;

        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getContrasena() { return contrasena; }
        public void setContrasena(String contrasena) { this.contrasena = contrasena; }
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        public String getCedula() { return cedula; }
        public void setCedula(String cedula) { this.cedula = cedula; }
    }
}
