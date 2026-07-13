package com.idat.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idat.springboot.model.Alumno;

public interface AlumnoJPA extends JpaRepository<Alumno, Long> {

}
