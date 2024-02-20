package com.app.biblioteca.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.app.biblioteca.models.entity.Prestamo;

public interface PrestamoService {
	

    Prestamo prestarLibro(Long idEstudiante, Long idLibro);

    void devolverLibro(Long idPrestamo);

    Page<Prestamo> listarPrestamos(Pageable pageable);

    Prestamo obtenerPrestamo(Long idPrestamo);

    Prestamo actualizarPrestamo(Long idPrestamo, Prestamo prestamoActualizado);

    void eliminarPrestamo(Long idPrestamo);

	Iterable<Prestamo> findAll();
}
