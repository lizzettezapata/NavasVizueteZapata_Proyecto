package com.app.biblioteca.controllers;

import com.app.biblioteca.models.entity.Libro;
import com.app.biblioteca.models.entity.Prestamo;
import com.app.biblioteca.models.repository.LibroRepository;
import com.app.biblioteca.models.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;
    
    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/listaPrestamo")
    public ResponseEntity<Iterable<Prestamo>> getAllPrestamos() {
        Iterable<Prestamo> prestamos = prestamoService.findAll();
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    @GetMapping("/verPrestamo/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        Optional<Prestamo> prestamo = prestamoService.findById(id);
        return prestamo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/crearPrestamo")
    public ResponseEntity<?> createPrestamo(@RequestBody Prestamo prestamo) {
        try {
            // Establecer la fecha de préstamo
            prestamo.setFechaPrestamo(LocalDate.now());

            // Obtener el libro asociado al préstamo por su ID
            Long libroId = prestamo.getLibro().getIdLibro();
            Libro libro = libroRepository.findById(libroId)
                                          .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            // Verificar si hay suficiente stock
            if (libro.getStock() > 0) {
                // Actualizar el stock del libro
                libro.setStock(libro.getStock() - 1);
                libroRepository.save(libro);

                // Asignar el libro actualizado al préstamo
                prestamo.setLibro(libro);

                // Guardar el préstamo
                Prestamo nuevoPrestamo = prestamoService.save(prestamo);

                return new ResponseEntity<>(nuevoPrestamo, HttpStatus.CREATED);
            } else {
                // Manejar la lógica de error si no hay suficiente stock
                return new ResponseEntity<>("Error: No hay suficiente stock disponible para el libro con ID: " + libro.getIdLibro(), HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            // Manejar otras excepciones si es necesario
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/editarPrestamo/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        Optional<Prestamo> existingPrestamo = prestamoService.findById(id);

        if (existingPrestamo.isPresent()) {
            prestamo.setIdPrestamo(id);
            Prestamo updatedPrestamo = prestamoService.save(prestamo);
            return new ResponseEntity<>(updatedPrestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminarPrestamo/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable Long id) {
        Optional<Prestamo> prestamoOptional = prestamoService.findById(id);

        if (prestamoOptional.isPresent()) {
            Prestamo prestamo = prestamoOptional.get();
            Libro libro = prestamo.getLibro();

            // Incrementar el stock del libro
            libro.setStock(libro.getStock() + 1);
            libroRepository.save(libro);

            // Eliminar el préstamo
            prestamoService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
