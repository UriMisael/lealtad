package com.bimbo.lealtad.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recompensas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private Integer valorEnPuntos;
}
