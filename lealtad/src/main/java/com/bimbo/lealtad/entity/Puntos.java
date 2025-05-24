package com.bimbo.lealtad.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "puntos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Puntos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Integer cantidad;
}
