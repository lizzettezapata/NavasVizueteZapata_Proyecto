package com.app.biblioteca.models.services;

import com.app.biblioteca.models.entity.Prestamo;
import com.app.biblioteca.models.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository repository;

    @Override
    public Iterable<Prestamo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        return repository.save(prestamo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
