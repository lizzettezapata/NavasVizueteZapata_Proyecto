package com.app.biblioteca.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.biblioteca.models.entity.Libro;

public interface LibroRepository extends PagingAndSortingRepository<Libro, Long> {
	@Transactional
    @Modifying
    @Query("UPDATE Libro l SET l.stock = :stock WHERE l.idLibro = :idLibro")
    void actualizarStock(@Param("idLibro") Long idLibro, @Param("stock") int stock);
}

