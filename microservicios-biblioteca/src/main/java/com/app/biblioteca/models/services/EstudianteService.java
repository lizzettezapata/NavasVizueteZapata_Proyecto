package com.app.biblioteca.models.services;

import com.app.biblioteca.models.entity.Estudiante;

import java.util.Optional;

public interface EstudianteService {

    Iterable<Estudiante> findAll();

    Optional<Estudiante> findById(Long id);

    Estudiante save(Estudiante estudiante);

    void deleteById(Long id);
}
