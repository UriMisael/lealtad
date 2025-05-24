package com.bimbo.lealtad.service;

import com.bimbo.lealtad.entity.Puntos;
import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.repository.PuntosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntosService {

    @Autowired
    private PuntosRepository puntosRepository;

    public void acumularPuntos(Usuario usuario, int cantidad) {
        Puntos puntos = new Puntos();
        puntos.setUsuario(usuario);
        puntos.setCantidad(cantidad);
        puntosRepository.save(puntos);
    }

    public int consultarSaldo(Usuario usuario) {
        List<Puntos> puntos = puntosRepository.findByUsuario(usuario);
        return puntos.stream().mapToInt(Puntos::getCantidad).sum();
    }
        /**
     * Método para descontar puntos de un usuario.
     * @param usuario El usuario al que se le descontarán los puntos.
     * @param cantidad La cantidad de puntos a descontar.
     */
    public void descontarPuntos(Usuario usuario, int cantidad) {
        List<Puntos> puntos = puntosRepository.findByUsuario(usuario);
    
        // Validación: asegurarse que hay suficientes puntos
        int totalPuntos = puntos.stream().mapToInt(Puntos::getCantidad).sum();
        if (totalPuntos < cantidad) {
            throw new IllegalArgumentException("El usuario no tiene suficientes puntos para descontar.");
        }
    
        // Descuento real
        int puntosRestantes = cantidad;
    
        for (Puntos punto : puntos) {
            if (puntosRestantes <= 0) break;
    
            if (punto.getCantidad() <= puntosRestantes) {
                puntosRestantes -= punto.getCantidad();
                puntosRepository.delete(punto);
            } else {
                punto.setCantidad(punto.getCantidad() - puntosRestantes);
                puntosRepository.save(punto);
                puntosRestantes = 0;
            }
        }
    }
    
}
