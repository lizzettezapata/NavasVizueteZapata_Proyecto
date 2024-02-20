package com.app.biblioteca.models.services;

import com.app.biblioteca.models.entity.Estudiante;
import com.app.biblioteca.models.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    @Override
    public Iterable<Estudiante> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
