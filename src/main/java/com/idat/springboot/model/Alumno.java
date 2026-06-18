package com.idat.springboot.model;

public class Alumno {

    private Long id;
    private String nombre;
    private Integer edad;

    public Alumno() {
    }

    public Alumno(Long id, String nombre, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

}
