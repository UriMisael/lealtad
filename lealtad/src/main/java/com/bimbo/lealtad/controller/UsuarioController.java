package com.bimbo.lealtad.controller;

import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        boolean registrado = usuarioService.registrarUsuario(usuario);
        if (registrado) {
            return ResponseEntity.ok("Usuario registrado correctamente");
        } else {
            return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso");
        }
    }
}
