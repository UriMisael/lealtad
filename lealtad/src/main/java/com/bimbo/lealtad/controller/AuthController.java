package com.bimbo.lealtad.controller;

import com.bimbo.lealtad.entity.Recompensa;
import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioController usuarioController; // Inyectamos UsuarioController

    @Autowired
    private PuntosController puntosController;

    @Autowired
    private RecompensaController recompensaController;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        // Llamamos al método register de UsuarioController
        return usuarioController.registrarUsuario(usuario);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails.getUsername());
    }

    @PostMapping("/puntos/acumular")
    public ResponseEntity<String> acumularPuntos(@RequestParam String nombreUsuario, @RequestParam int cantidad) {
        return puntosController.acumularPuntos(nombreUsuario, cantidad);
    }

    @GetMapping("/puntos/saldo")
    public ResponseEntity<Integer> consultarSaldo(@RequestParam String nombreUsuario) {
        return puntosController.consultarSaldo(nombreUsuario);
    }

    @GetMapping("/recompensas")
    public ResponseEntity<List<Recompensa>> listarRecompensas() {
        return recompensaController.listarRecompensas();
    }

    @PostMapping("/recompensas/crear")
    public ResponseEntity<Recompensa> crearRecompensa(@RequestParam String nombre, @RequestParam int valorEnPuntos) {
        return recompensaController.crearRecompensa(nombre, valorEnPuntos);
    }

    @PostMapping("/recompensas/canjear")
    public ResponseEntity<String> canjearRecompensa(@RequestParam String nombreUsuario, @RequestParam Long recompensaId) {
        return recompensaController.canjearRecompensa(nombreUsuario, recompensaId);
    }
}
