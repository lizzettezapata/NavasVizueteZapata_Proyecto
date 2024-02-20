package com.app.biblioteca.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.biblioteca.models.entity.Libro;
import com.app.biblioteca.models.services.LibroService;

@RestController
public class LibroController {

    @Autowired
    private LibroService service;

    @GetMapping("/listaLibro")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/verLibro/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<Libro> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(o.get());
    }

    @PostMapping("/crearLibro")
    public ResponseEntity<?> crear(@Valid @RequestBody Libro libro, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }

        Libro libroDb = service.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroDb);
    }
    
    @PutMapping("/editarLibro/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Libro libro, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }

        Optional<Libro> libroDbOptional = service.findById(id);
        if (!libroDbOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Libro libroDb = libroDbOptional.get();
        libroDb.setNombreLibro(libro.getNombreLibro());
        libroDb.setStock(libro.getStock());
        libroDb.setAutor(libro.getAutor());
        libroDb.setAnoPublicacion(libro.getAnoPublicacion());
        libroDb.setGenero(libro.getGenero());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(libroDb));
    }

    @DeleteMapping("/eliminarLibro/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Libro> libroDbOptional = service.findById(id);
        if (!libroDbOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        return ResponseEntity.badRequest().body(errores);
    }
}
