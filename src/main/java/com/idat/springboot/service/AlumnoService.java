package com.idat.springboot.service;

import org.springframework.data.domain.Pageable;
import com.idat.springboot.dto.PageResponse;
import com.idat.springboot.model.Alumno;
import com.idat.springboot.dto.AlumnoRequest;

public interface AlumnoService {

    PageResponse<Alumno> getAllAlumnos(Pageable pageable);

    PageResponse<Alumno> filter(String nombre, String email, Boolean activo, Integer edadMin, Integer edadMax, Pageable pageable);

    Alumno getAlumnoById(Long id);

    Alumno createAlumno(AlumnoRequest alumno);

    Alumno updateAlumno(Long id, AlumnoRequest alumno);

    void deleteAlumno(Long id);

}
