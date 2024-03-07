package com.app.biblioteca.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import com.app.biblioteca.models.entity.Libro;

public interface LibroService {

    Iterable<Libro> findAll();

    Page<Libro> findAll(Pageable pageable);

    Optional<Libro> findById(Long id);

    Libro save(Libro libro);

    void deleteById(Long id);
}
