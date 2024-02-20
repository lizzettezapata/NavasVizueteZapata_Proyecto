package com.app.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.biblioteca.models.entity.Prestamo;
import com.app.biblioteca.models.services.PrestamoService;

@RestController
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("/prestar")
    public ResponseEntity<?> prestarLibro(@RequestParam Long idEstudiante, @RequestParam Long idLibro) {
        try {
            Prestamo prestamo = prestamoService.prestarLibro(idEstudiante, idLibro);
            return ResponseEntity.ok().body(prestamo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se puede realizar el préstamo. Motivo: " + e.getMessage());
        }
    }

    @PostMapping("/devolver/{idPrestamo}")
    public ResponseEntity<?> devolverLibro(@PathVariable Long idPrestamo) {
        try {
            prestamoService.devolverLibro(idPrestamo);
            return ResponseEntity.ok().body("Libro devuelto exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se puede realizar la devolución. Motivo: " + e.getMessage());
        }
    }

    @GetMapping("/listaPrestamo")
    public ResponseEntity<Iterable<Prestamo>> listarPrestamos() {
        return ResponseEntity.ok().body(prestamoService.findAll());
    }

    @GetMapping("/verPrestamo/{idPrestamo}")
    public ResponseEntity<?> verPrestamo(@PathVariable Long idPrestamo) {
        Prestamo prestamo = prestamoService.obtenerPrestamo(idPrestamo);
        if (prestamo != null) {
            return ResponseEntity.ok().body(prestamo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizarPrestamo/{idPrestamo}")
    public ResponseEntity<?> actualizarPrestamo(@PathVariable Long idPrestamo, @RequestBody Prestamo prestamo) {
        try {
            Prestamo prestamoActualizado = prestamoService.actualizarPrestamo(idPrestamo, prestamo);
            return ResponseEntity.ok().body(prestamoActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarPrestamo/{idPrestamo}")
    public ResponseEntity<?> eliminarPrestamo(@PathVariable Long idPrestamo) {
        try {
            prestamoService.eliminarPrestamo(idPrestamo);
            return ResponseEntity.ok().body("Prestamo eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se puede realizar la eliminación del préstamo. Motivo: " + e.getMessage());
        }
    }
}