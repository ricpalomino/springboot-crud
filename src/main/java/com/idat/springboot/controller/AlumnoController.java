package com.idat.springboot.controller;

import com.idat.springboot.service.AlumnoService;
import com.idat.springboot.model.Alumno;
import com.idat.springboot.dto.AlumnoRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos() {
        return ResponseEntity.ok(alumnoService.getAllAlumnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long id) {
        Alumno alumno = alumnoService.getAlumnoById(id);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno);
    }

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody AlumnoRequest request) {
        Alumno alumno = alumnoService.createAlumno(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id, @RequestBody AlumnoRequest request) {
        Alumno alumno = alumnoService.updateAlumno(id, request);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        alumnoService.deleteAlumno(id);
        return ResponseEntity.noContent().build();
    }

}
