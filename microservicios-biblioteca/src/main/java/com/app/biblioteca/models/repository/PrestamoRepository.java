package com.app.biblioteca.models.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.biblioteca.models.entity.Prestamo;

public interface PrestamoRepository extends PagingAndSortingRepository<Prestamo, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Prestamo p SET p.fechaDevolucion = :fechaDevolucion WHERE p.idPrestamo = :idPrestamo")
    void actualizarFechaDevolucion(@Param("idPrestamo") Long idPrestamo, @Param("fechaDevolucion") LocalDate fechaDevolucion);
}
