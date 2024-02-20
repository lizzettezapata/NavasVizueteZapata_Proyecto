package com.app.biblioteca.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.biblioteca.models.entity.Libro;
import com.app.biblioteca.models.repository.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository repository;

    @Override
    public Iterable<Libro> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Libro> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Libro save(Libro libro) {
        return repository.save(libro);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
