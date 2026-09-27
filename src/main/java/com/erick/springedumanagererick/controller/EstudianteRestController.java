package com.erick.springedumanagererick.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erick.springedumanagererick.model.Estudiante;
import com.erick.springedumanagererick.repository.EstudianteRepository;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteRestController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @GetMapping
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        return estudiante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id, @RequestBody Estudiante datosNuevos) {
        return estudianteRepository.findById(id)
                .map(estudianteExistente -> {
                    estudianteExistente.setNombre(datosNuevos.getNombre());
                    estudianteExistente.setEdad(datosNuevos.getEdad());
                    estudianteExistente.setCorreo(datosNuevos.getCorreo());
                    return ResponseEntity.ok(estudianteRepository.save(estudianteExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!estudianteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        estudianteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}