package com.idat.springboot.controller;

import com.idat.springboot.service.AlumnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Alumno Controller", description = "API para gestionar alumnos")
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @Operation(summary = "Obtener todos los alumnos", description = "Devuelve una lista de todos los alumnos registrados")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Alumno>>> getAlumnos() {
        return ResponseEntity.ok(ApiResponseUtil.success("Alumnos encontrados", alumnoService.getAllAlumnos()));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<Alumno>>> filter(
        @RequestParam(required = false) String nombre) {
        List<Alumno> alumnos = alumnoService.filter(nombre);
        return ResponseEntity.ok(ApiResponseUtil.success("Alumnos encontrados", alumnos));
    }

    @Operation(summary = "Obtener un alumno por ID", description = "Devuelve un alumno específico según su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Alumno>> getAlumnoById(@PathVariable Long id) {
        Alumno alumno = alumnoService.getAlumnoById(id);
        return ResponseEntity.ok(ApiResponseUtil.success("Alumno encontrado", alumno));
    }

    @Operation(summary = "Crear un nuevo alumno", description = "Crea un nuevo alumno con los datos proporcionados")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Alumno creado exitosamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error de validación de los datos del alumno")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<Alumno>> crearAlumno(@Valid @RequestBody AlumnoRequest request) {
        Alumno alumno = alumnoService.createAlumno(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponseUtil.success("Alumno creado", alumno));
    }

    @Operation(summary = "Actualizar un alumno", description = "Actualiza los datos de un alumno existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Alumno>> actualizarAlumno(@PathVariable Long id, @Valid @RequestBody AlumnoRequest request) {
        Alumno alumno = alumnoService.updateAlumno(id, request);
        return ResponseEntity.ok(ApiResponseUtil.success("Alumno actualizado", alumno));
    }

    @Operation(summary = "Eliminar un alumno", description = "Elimina un alumno específico según su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarAlumno(@PathVariable Long id) {
        alumnoService.deleteAlumno(id);
        return ResponseEntity.ok(ApiResponseUtil.success("Alumno eliminado", null));
    }

}
