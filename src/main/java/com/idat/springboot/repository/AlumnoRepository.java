package com.idat.springboot.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.idat.springboot.model.*;

@Repository
public class AlumnoRepository {

    private final List<Alumno> alumnos = new ArrayList<>();

    public AlumnoRepository() {
        Alumno alumno1 = new Alumno();
        alumno1.setId(1L);
        alumno1.setNombre("Juan Pérez");
        alumno1.setEdad(20);
        alumnos.add(alumno1);

        Alumno alumno2 = new Alumno();
        alumno2.setId(2L);
        alumno2.setNombre("María Gómez");
        alumno2.setEdad(22);
        alumnos.add(alumno2);
    }

    public List<Alumno> findAll() {
        return alumnos;
    }

    public Alumno findById(Long id) {
        return findAll().stream()
                .filter(alumno -> alumno.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
