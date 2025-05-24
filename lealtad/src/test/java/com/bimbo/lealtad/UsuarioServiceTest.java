package com.bimbo.lealtad;

import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.repository.UsuarioRepository;
import com.bimbo.lealtad.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuario_UsuarioNuevo_DevuelveTrue() {
        Usuario usuario = new Usuario(null, "testuser", "password");
        Mockito.when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(false);

        boolean resultado = usuarioService.registrarUsuario(usuario);

        Assertions.assertTrue(resultado);
        Assertions.assertEquals("password", usuario.getContraseña()); // Contraseña sin codificar
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuario);
    }

    @Test
    void registrarUsuario_UsuarioExistente_DevuelveFalse() {
        // Arrange
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(true);

        // Act
        boolean resultado = usuarioService.registrarUsuario(new Usuario(null, "testuser", "password"));

        // Assert
        assertFalse(resultado);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void obtenerPorNombreUsuario_UsuarioExistente_DevuelveUsuario() {
        // Arrange
        Usuario usuario = new Usuario(1L, "testuser", "password");
        when(usuarioRepository.findByNombreUsuario("testuser")).thenReturn(Optional.of(usuario));

        // Act
        Usuario resultado = usuarioService.obtenerPorNombreUsuario("testuser");

        // Assert
        assertNotNull(resultado);
        assertEquals("testuser", resultado.getNombreUsuario());
    }

    @Test
    void obtenerPorNombreUsuario_UsuarioNoExistente_DevuelveNull() {
        // Arrange
        when(usuarioRepository.findByNombreUsuario("testuser")).thenReturn(Optional.empty());

        // Act
        Usuario resultado = usuarioService.obtenerPorNombreUsuario("testuser");

        // Assert
        assertNull(resultado);
    }
}