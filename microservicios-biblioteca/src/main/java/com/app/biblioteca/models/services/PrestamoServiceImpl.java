package com.app.biblioteca.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.biblioteca.models.entity.Libro;
import com.app.biblioteca.models.entity.Prestamo;
import com.app.biblioteca.models.entity.Estudiante;
import com.app.biblioteca.models.repository.LibroRepository;
import com.app.biblioteca.models.repository.PrestamoRepository;
import com.app.biblioteca.models.repository.EstudianteRepository;


@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private EstudianteRepository estudianteRepository; // Inyecta el repositorio de Estudiante

    
    public Iterable<Prestamo> findAll(){
        return prestamoRepository.findAll();
    }
    
    @Override
    @Transactional
    public Prestamo prestarLibro(Long idEstudiante, Long idLibro) {
        Optional<Libro> libroOptional = libroRepository.findById(idLibro);

        if (libroOptional.isPresent() && libroOptional.get().getStock() > 0) {
            Libro libro = libroOptional.get();
            libro.setStock(libro.getStock() - 1);
            libroRepository.actualizarStock(libro.getIdLibro(), libro.getStock());

            Prestamo prestamo = new Prestamo();
            Optional<Estudiante> estudianteOptional = estudianteRepository.findById(idEstudiante);

            if (estudianteOptional.isPresent()) {
                Estudiante estudiante = estudianteOptional.get();
                prestamo.setEstudiante(estudiante);
                prestamo.setLibro(libro); 
                prestamoRepository.save(prestamo);
                return prestamo;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    @Transactional
    public void devolverLibro(Long idPrestamo) {
        Optional<Prestamo> prestamoOptional = prestamoRepository.findById(idPrestamo);

        if (prestamoOptional.isPresent()) {
            Prestamo prestamo = prestamoOptional.get();
            Libro libro = prestamo.getLibro();
            libro.setStock(libro.getStock() + 1);
            libroRepository.actualizarStock(libro.getIdLibro(), libro.getStock());

            prestamoRepository.deleteById(idPrestamo);
        }
    }

    @Override
    public Page<Prestamo> listarPrestamos(Pageable pageable) {
        return prestamoRepository.findAll(pageable);
    }

    @Override
    public Prestamo obtenerPrestamo(Long idPrestamo) {
        return prestamoRepository.findById(idPrestamo).orElse(null);
    }

    @Override
    @Transactional
    public Prestamo actualizarPrestamo(Long idPrestamo, Prestamo prestamoActualizado) {
        Optional<Prestamo> prestamoOptional = prestamoRepository.findById(idPrestamo);

        if (prestamoOptional.isPresent()) {
            Prestamo prestamo = prestamoOptional.get();
            // Actualiza los atributos del prestamo según tus necesidades
            prestamo.setFechaDevolucion(prestamoActualizado.getFechaDevolucion());
            prestamoRepository.save(prestamo);
            return prestamo;
        } else {
            return null;
        }
    }

    @Override
    public void eliminarPrestamo(Long idPrestamo) {
        prestamoRepository.deleteById(idPrestamo);
    }
}
