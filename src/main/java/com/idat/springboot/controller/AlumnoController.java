package com.idat.springboot.controller;

import com.idat.springboot.service.AlumnoService;
import com.idat.springboot.common.ApiResponseUtil;
import com.idat.springboot.common.ApiResponse;
import jakarta.validation.Valid;

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
    public ResponseEntity<ApiResponse<List<Alumno>>> getAlumnos() {
        return ResponseEntity.ok(ApiResponseUtil.success("Alumnos encontrados", alumnoService.getAllAlumnos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Alumno>> getAlumnoById(@PathVariable Long id) {
        Alumno alumno = alumnoService.getAlumnoById(id);
        return ResponseEntity.ok(ApiResponseUtil.success("Alumno encontrado", alumno));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Alumno>> crearAlumno(@Valid @RequestBody AlumnoRequest request) {
        Alumno alumno = alumnoService.createAlumno(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponseUtil.success("Alumno creado", alumno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Alumno>> actualizarAlumno(@PathVariable Long id, @Valid @RequestBody AlumnoRequest request) {
        Alumno alumno = alumnoService.updateAlumno(id, request);
        return ResponseEntity.ok(ApiResponseUtil.success("Alumno actualizado", alumno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarAlumno(@PathVariable Long id) {
        alumnoService.deleteAlumno(id);
        return ResponseEntity.ok(ApiResponseUtil.success("Alumno eliminado", null));
    }

}
