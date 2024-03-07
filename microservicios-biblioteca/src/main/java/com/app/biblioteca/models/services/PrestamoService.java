package com.app.biblioteca.models.services;

import com.app.biblioteca.models.entity.Prestamo;

import java.util.Optional;

public interface PrestamoService {

    Iterable<Prestamo> findAll();

    Optional<Prestamo> findById(Long id);

    Prestamo save(Prestamo prestamo);

    void deleteById(Long id);
}
