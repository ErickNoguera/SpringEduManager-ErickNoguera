package com.erick.springedumanagererick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.erick.springedumanagererick.model.Estudiante;
import com.erick.springedumanagererick.repository.EstudianteRepository;

@Controller
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @GetMapping("/estudiantes")
    public String listar(Model model) {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes";
    }

    @GetMapping("/estudiantes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante-form";
    }

    @PostMapping("/estudiantes/guardar")
    public String guardar(@ModelAttribute Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";
    }
}