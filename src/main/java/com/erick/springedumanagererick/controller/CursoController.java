package com.erick.springedumanagererick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.erick.springedumanagererick.model.Curso;
import com.erick.springedumanagererick.repository.CursoRepository;

@Controller
public class CursoController {

	private final CursoRepository cursoRepository;
	
	@Autowired
	public CursoController(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}
	
	@GetMapping("/cursos")
	public String listar(Model model) {
		List<Curso> cursos = cursoRepository.findAll();
		model.addAttribute("cursos", cursos);
		return "cursos";
	}
	
	@GetMapping("/cursos/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("curso", new Curso());
		return "curso-form";
	}
	
	@PostMapping("/cursos/guardar")
	public String guardar(@ModelAttribute Curso curso) {
		cursoRepository.save(curso);
		return "redirect:/cursos";
	}
}
