package com.bimbo.lealtad;


import com.bimbo.lealtad.controller.UsuarioController;
import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuario_UsuarioNuevo_DevuelveOk() {
        // Arrange
        Usuario usuario = new Usuario(null, "testuser", "password");
        when(usuarioService.registrarUsuario(any(Usuario.class))).thenReturn(true);

        // Act
        ResponseEntity<String> respuesta = usuarioController.registrarUsuario(usuario);

        // Assert
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("Usuario registrado correctamente", respuesta.getBody());
        verify(usuarioService, times(1)).registrarUsuario(any(Usuario.class));
    }

    @Test
    void registrarUsuario_UsuarioExistente_DevuelveBadRequest() {
        // Arrange
        Usuario usuario = new Usuario(null, "testuser", "password");
        when(usuarioService.registrarUsuario(any(Usuario.class))).thenReturn(false);

        // Act
        ResponseEntity<String> respuesta = usuarioController.registrarUsuario(usuario);

        // Assert
        assertEquals(400, respuesta.getStatusCode().value());
        assertEquals("El nombre de usuario ya está en uso", respuesta.getBody());
        verify(usuarioService, times(1)).registrarUsuario(any(Usuario.class));
    }
}