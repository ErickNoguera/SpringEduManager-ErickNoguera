package com.erick.springedumanagererick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erick.springedumanagererick.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
