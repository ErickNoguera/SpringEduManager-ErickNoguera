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

import com.erick.springedumanagererick.model.Curso;
import com.erick.springedumanagererick.repository.CursoRepository;

@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoRestController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody Curso datosNuevos) {
        return cursoRepository.findById(id)
                .map(cursoExistente -> {
                    cursoExistente.setNombreCurso(datosNuevos.getNombreCurso());
                    cursoExistente.setDescripcion(datosNuevos.getDescripcion());
                    cursoExistente.setHorario(datosNuevos.getHorario());
                    cursoExistente.setCupos(datosNuevos.getCupos());
                    return ResponseEntity.ok(cursoRepository.save(cursoExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!cursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}