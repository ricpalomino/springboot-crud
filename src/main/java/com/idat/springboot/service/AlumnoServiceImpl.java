package com.idat.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.idat.springboot.model.Alumno;
import com.idat.springboot.dto.AlumnoRequest;
import com.idat.springboot.repository.AlumnoJPA;
import com.idat.springboot.exception.AlumnoNotFoundException;
import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private static final Logger logger = LoggerFactory.getLogger(AlumnoServiceImpl.class);
    private final AlumnoJPA alumnoRepository;

    public AlumnoServiceImpl(AlumnoJPA alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<Alumno> getAllAlumnos() {
        logger.info("obteniendo todos los alumnos");
        return alumnoRepository.findAll();
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
    public void deleteAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }

}
