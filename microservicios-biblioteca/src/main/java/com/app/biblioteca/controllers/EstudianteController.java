package com.app.biblioteca.controllers;

import com.app.biblioteca.models.entity.Estudiante;
import com.app.biblioteca.models.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class EstudianteController {

    @Autowired
    private EstudianteService service;
    
    @GetMapping("/listaEstudiante")
    public ResponseEntity<Iterable<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/verEstudiante/{id}")
    public ResponseEntity<Estudiante> verEstudiante(@PathVariable Long id) {
        Optional<Estudiante> estudiante = service.findById(id);
        return estudiante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/crearEstudiante")
    public ResponseEntity<?> crearEstudiante(@Valid @RequestBody Estudiante estudiante, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Estudiante estudianteDb = service.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteDb);
    }
    
    @DeleteMapping("/eliminarEstudiante/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
