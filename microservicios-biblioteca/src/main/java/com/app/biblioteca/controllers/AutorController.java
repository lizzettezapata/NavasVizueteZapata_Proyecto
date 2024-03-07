package com.app.biblioteca.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.app.biblioteca.models.entity.Autor;
import com.app.biblioteca.models.services.AutorService;

@RestController
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/listaAutor")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(autorService.findAll());
    }

    @GetMapping("/verAutor/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<Autor> o = autorService.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(o.get());
    }

    @PostMapping("/crearAutor")
    public ResponseEntity<?> crear(@Valid @RequestBody Autor autor, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }

        Autor autorDb = autorService.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorDb);
    }

    @PutMapping("/editarAutor/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Autor autor, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }

        Optional<Autor> autorDb = autorService.findById(id);
        if (!autorDb.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        autorDb.get().setNombreAutor(autor.getNombreAutor());
        autorDb.get().setNacionalidad(autor.getNacionalidad());

        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autorDb.get()));
    }

    @DeleteMapping("/eliminarAutor/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Autor> autorDb = autorService.findById(id);
        if (!autorDb.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
