package com.idat.springboot.exception;

public class AlumnoNotFoundException extends RuntimeException {

    public AlumnoNotFoundException(Long id) {
        super("Alumno con ID " + id + " no encontrado.");
    }

}
