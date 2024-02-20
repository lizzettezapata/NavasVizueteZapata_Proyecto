package com.app.biblioteca.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.biblioteca.models.entity.Autor;
import com.app.biblioteca.models.repository.AutorRepository;

import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository repository;

    @Override
    public Iterable<Autor> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Autor> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Autor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Autor save(Autor autor) {
        return repository.save(autor);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
