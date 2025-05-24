package com.bimbo.lealtad;

import com.bimbo.lealtad.entity.Puntos;
import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.repository.PuntosRepository;
import com.bimbo.lealtad.service.PuntosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PuntosServiceTest {

    @Mock
    private PuntosRepository puntosRepository;

    @InjectMocks
    private PuntosService puntosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void acumularPuntos_GuardaPuntosCorrectamente() {
        // Arrange
        Usuario usuario = new Usuario(1L, "testuser", "password");

        // Act
        puntosService.acumularPuntos(usuario, 100);

        // Assert
        verify(puntosRepository, times(1)).save(any(Puntos.class));
    }

    @Test
    void consultarSaldo_DevuelveSumaDePuntos() {
        // Arrange
        Usuario usuario = new Usuario(1L, "testuser", "password");
        List<Puntos> puntos = List.of(new Puntos(1L, usuario, 50), new Puntos(2L, usuario, 100));
        when(puntosRepository.findByUsuario(usuario)).thenReturn(puntos);

        // Act
        int saldo = puntosService.consultarSaldo(usuario);

        // Assert
        assertEquals(150, saldo);
    }
}