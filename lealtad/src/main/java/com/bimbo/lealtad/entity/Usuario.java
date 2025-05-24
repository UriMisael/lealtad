package com.bimbo.lealtad.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    @Column(nullable = false)
    private String contraseña;

    /**
     * Método para codificar la contraseña antes de guardarla.
     * @param passwordEncoder el codificador de contraseñas (PasswordEncoder)
     */
    public void codificarContraseña(PasswordEncoder passwordEncoder) {
        this.contraseña = passwordEncoder.encode(this.contraseña);
    }
}
