package com.idat.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.idat.springboot.model.Alumno;

public interface AlumnoJPA extends JpaRepository<Alumno, Long> {

    List<Alumno> findByNombreContainingIgnoreCase(String nombre);

    Page<Alumno> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    List<Alumno> findByEmailContainingIgnoreCase(String email);

    List<Alumno> findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(String nombre, String email);

    List<Alumno> findByActivoTrue();

    List<Alumno> findByActivoFalse();

    List<Alumno> findByEdadGreaterThanEqual(Integer edad);

    List<Alumno> findByEdadLessThanEqual(Integer edad);

    List<Alumno> findByEdadBetween(Integer edadMin, Integer edadMax);

    @Query("SELECT a FROM Alumno a WHERE " +
            "(:nombre IS NULL OR LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:email IS NULL OR LOWER(a.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:activo IS NULL OR a.activo = :activo) AND " +
            "(:edadMin IS NULL OR a.edad >= :edadMin) AND " +
            "(:edadMax IS NULL OR a.edad <= :edadMax)")
    Page<Alumno> buscar(@Param("nombre") String nombre, @Param("email") String email, @Param("activo") Boolean activo, @Param("edadMin") Integer edadMin, @Param("edadMax") Integer edadMax, Pageable pageable);
}