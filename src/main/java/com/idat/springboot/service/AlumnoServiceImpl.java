package com.idat.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.idat.springboot.model.Alumno;
import com.idat.springboot.dto.AlumnoRequest;
import com.idat.springboot.dto.PageResponse;
import com.idat.springboot.repository.AlumnoJPA;
import com.idat.springboot.exception.AlumnoNotFoundException;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private static final Logger logger = LoggerFactory.getLogger(AlumnoServiceImpl.class);
    private final AlumnoJPA alumnoRepository;

    public AlumnoServiceImpl(AlumnoJPA alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public PageResponse<Alumno> getAllAlumnos(Pageable pageable) {
        logger.info("obteniendo todos los alumnos");
        Page<Alumno> page = alumnoRepository.findAll(pageable);
        return new PageResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.getContent()
        );
    }

    @Override
    public Alumno getAlumnoById(Long id) {
        logger.info("obteniendo alumno por ID: {}", id);
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new AlumnoNotFoundException(id));
    }

    @Override
    public Alumno createAlumno(AlumnoRequest alumnoRequest) {
        logger.info("creando nuevo alumno: {}", alumnoRequest.getNombre());
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoRequest.getNombre());
        alumno.setEdad(alumnoRequest.getEdad());
        alumno.setEmail(alumnoRequest.getEmail());
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno updateAlumno(Long id, AlumnoRequest alumnoRequest) {
        Alumno alumno = getAlumnoById(id);
        if (alumno == null) {
            return null;
        }
        alumno.setNombre(alumnoRequest.getNombre());
        alumno.setEdad(alumnoRequest.getEdad());
        alumno.setEmail(alumnoRequest.getEmail());
        alumno.setActivo(alumnoRequest.getActivo());
        alumnoRepository.save(alumno);
        return alumno;
    }

    @Override
    public PageResponse<Alumno> filter(String nombre, String email, Boolean activo, Integer edadMin, Integer edadMax, Pageable pageable) {
        logger.info("filtrando alumnos por criterios - nombre: {}, email: {}, activo: {}, edadMin: {}, edadMax: {}",
                nombre, email, activo, edadMin, edadMax);
        Page<Alumno> page = alumnoRepository.buscar(nombre, email, activo, edadMin, edadMax, pageable);
        return new PageResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.getContent()
        );
    }

    @Override
    public void deleteAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }

}
