package com.erick.springedumanagererick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erick.springedumanagererick.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
	
}
