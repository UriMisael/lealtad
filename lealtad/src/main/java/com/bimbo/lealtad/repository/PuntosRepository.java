package com.bimbo.lealtad.repository;

import com.bimbo.lealtad.entity.Puntos;
import com.bimbo.lealtad.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntosRepository extends JpaRepository<Puntos, Long> {
    List<Puntos> findByUsuario(Usuario usuario);
}
