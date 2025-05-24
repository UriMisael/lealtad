package com.bimbo.lealtad.controller;

import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.service.PuntosService;
import com.bimbo.lealtad.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/puntos")
public class PuntosController {

    @Autowired
    private PuntosService puntosService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/acumular")
    public ResponseEntity<String> acumularPuntos(@RequestParam String nombreUsuario, @RequestParam int cantidad) {
        Usuario usuario = usuarioService.obtenerPorNombreUsuario(nombreUsuario);
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        puntosService.acumularPuntos(usuario, cantidad);
        return ResponseEntity.ok("Puntos acumulados correctamente");
    }

    @GetMapping("/saldo")
    public ResponseEntity<Integer> consultarSaldo(@RequestParam String nombreUsuario) {
        Usuario usuario = usuarioService.obtenerPorNombreUsuario(nombreUsuario);
        if (usuario == null) {
            return ResponseEntity.badRequest().body(null);
        }
        int saldo = puntosService.consultarSaldo(usuario);
        return ResponseEntity.ok(saldo);
    }
}
