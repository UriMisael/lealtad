package com.bimbo.lealtad.controller;

import com.bimbo.lealtad.entity.Recompensa;
import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.service.PuntosService;
import com.bimbo.lealtad.service.RecompensaService;
import com.bimbo.lealtad.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recompensas")
public class RecompensaController {

    @Autowired
    private RecompensaService recompensaService;

    @Autowired
    private PuntosService puntosService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Recompensa>> listarRecompensas() {
        return ResponseEntity.ok(recompensaService.listarRecompensas());
    }

    @PostMapping("/canjear")
    public ResponseEntity<String> canjearRecompensa(@RequestParam String nombreUsuario, @RequestParam Long recompensaId) {
        Usuario usuario = usuarioService.obtenerPorNombreUsuario(nombreUsuario);
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        int saldoActual = puntosService.consultarSaldo(usuario);
        Recompensa recompensa = recompensaService.listarRecompensas().stream()
                .filter(r -> r.getId().equals(recompensaId))
                .findFirst()
                .orElse(null);

        if (recompensa == null) {
            return ResponseEntity.badRequest().body("Recompensa no encontrada");
        }

        if (recompensaService.canjearRecompensa(recompensa, usuario, saldoActual)) {
            return ResponseEntity.ok("Recompensa canjeada correctamente");
        } else {
            return ResponseEntity.badRequest().body("Saldo insuficiente");
        }
    }
     /**
     * Endpoint para crear una nueva recompensa.
     * @param nombre Nombre de la recompensa.
     * @param valorEnPuntos Valor en puntos de la recompensa.
     * @return La recompensa creada.
     */
    @PostMapping("/crear")
    public ResponseEntity<Recompensa> crearRecompensa(@RequestParam String nombre, @RequestParam int valorEnPuntos) {
        Recompensa recompensa = recompensaService.generarRecompensa(nombre, valorEnPuntos);
        return ResponseEntity.ok(recompensa);
    }
}
