package com.bimbo.lealtad.service;

import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean registrarUsuario(Usuario usuario) {
        // Validamos si el usuario ya existe
        if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
            return false; // Usuario ya registrado
        }
        // Guardamos la contraseña directamente sin codificar
        usuarioRepository.save(usuario);
        return true;
    }

    public Usuario obtenerPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
    }
}
