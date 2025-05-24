package com.bimbo.lealtad.service;


import com.bimbo.lealtad.entity.Recompensa;
import com.bimbo.lealtad.entity.Usuario;
import com.bimbo.lealtad.repository.RecompensaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecompensaService {

    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private PuntosService puntosService;

    public List<Recompensa> listarRecompensas() {
        return recompensaRepository.findAll();
    }

    /**
     * Método para canjear una recompensa si el usuario tiene suficientes puntos.
     * @param recompensa La recompensa a canjear.
     * @param usuario El usuario que realiza el canje.
     * @param saldoActual El saldo actual de puntos del usuario.
     * @return `true` si el canje fue exitoso, `false` en caso contrario.
     */
    public boolean canjearRecompensa(Recompensa recompensa, Usuario usuario, int saldoActual) {
        if (saldoActual >= recompensa.getValorEnPuntos()) {
            // Descontar los puntos del usuario
            puntosService.descontarPuntos(usuario, recompensa.getValorEnPuntos());

            // Registrar el canje
            registrarCanje(recompensa, usuario);

            return true;
        }
        return false;
    }

    /**
     * Método para registrar el canje de una recompensa.
     * @param recompensa La recompensa canjeada.
     * @param usuario El usuario que realizó el canje.
     */
    private void registrarCanje(Recompensa recompensa, Usuario usuario) {
        // Aquí puedes implementar la lógica para guardar el registro del canje en la base de datos
        System.out.println("Canje registrado: " + recompensa.getNombre() + " por el usuario " + usuario.getNombreUsuario());
    }

        /**
     * Método para generar una nueva recompensa.
     * @param nombre Nombre de la recompensa.
     * @param valorEnPuntos Valor en puntos de la recompensa.
     * @return La recompensa creada.
     */
    public Recompensa generarRecompensa(String nombre, int valorEnPuntos) {
        Recompensa recompensa = new Recompensa();
        recompensa.setNombre(nombre);
        recompensa.setValorEnPuntos(valorEnPuntos);
        return recompensaRepository.save(recompensa);
    }
}
