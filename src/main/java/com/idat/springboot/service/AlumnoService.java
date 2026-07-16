package com.idat.springboot.service;

import java.util.List;
import com.idat.springboot.model.Alumno;
import com.idat.springboot.dto.AlumnoRequest;

public interface AlumnoService {

    List<Alumno> getAllAlumnos();

    List<Alumno> filter(String nombre);

    Alumno getAlumnoById(Long id);

    Alumno createAlumno(AlumnoRequest alumno);

    Alumno updateAlumno(Long id, AlumnoRequest alumno);

    void deleteAlumno(Long id);

}
