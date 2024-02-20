package com.app.biblioteca.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.biblioteca.models.entity.Autor;

import java.util.Optional;

public interface AutorService {

    Iterable<Autor> findAll();

    Page<Autor> findAll(Pageable pageable);

    Optional<Autor> findById(Long id);

    Autor save(Autor autor);

    void deleteById(Long id);
}
