package com.app.biblioteca.models.repository;

import com.app.biblioteca.models.entity.Estudiante;
import org.springframework.data.repository.CrudRepository;

public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {
}
