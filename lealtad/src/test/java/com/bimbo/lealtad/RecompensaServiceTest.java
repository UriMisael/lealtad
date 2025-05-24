package com.bimbo.lealtad;

import com.bimbo.lealtad.entity.Recompensa;
import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.repository.RecompensaRepository;
import com.bimbo.lealtad.service.PuntosService;
import com.bimbo.lealtad.service.RecompensaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecompensaServiceTest {

    @Mock
    private RecompensaRepository recompensaRepository;

    @Mock
    private PuntosService puntosService;

    @InjectMocks
    private RecompensaService recompensaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarRecompensas_DevuelveListaDeRecompensas() {
        // Arrange
        List<Recompensa> recompensas = List.of(new Recompensa(1L, "Recompensa 1", 100));
        when(recompensaRepository.findAll()).thenReturn(recompensas);

        // Act
        List<Recompensa> resultado = recompensaService.listarRecompensas();

        // Assert
        assertEquals(1, resultado.size());
        assertEquals("Recompensa 1", resultado.get(0).getNombre());
    }

    @Test
    void canjearRecompensa_ConSaldoSuficiente_RegistraCanje() {
        // Arrange
        Usuario usuario = new Usuario(1L, "testuser", "password");
        Recompensa recompensa = new Recompensa(1L, "Recompensa 1", 300);
        int saldoActual = 500;

        // Act
        boolean resultado = recompensaService.canjearRecompensa(recompensa, usuario, saldoActual);

        // Assert
        assertTrue(resultado);
        verify(puntosService, times(1)).descontarPuntos(usuario, 300);
    }

    @Test
    void canjearRecompensa_ConSaldoInsuficiente_NoRegistraCanje() {
        // Arrange
        Usuario usuario = new Usuario(1L, "testuser", "password");
        Recompensa recompensa = new Recompensa(1L, "Recompensa 1", 300);
        int saldoActual = 200;

        // Act
        boolean resultado = recompensaService.canjearRecompensa(recompensa, usuario, saldoActual);

        // Assert
        assertFalse(resultado);
        verify(puntosService, never()).descontarPuntos(any(Usuario.class), anyInt());
    }
}