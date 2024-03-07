package com.app.biblioteca.models.repository;

import org.springframework.data.repository.CrudRepository;


import com.app.biblioteca.models.entity.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

}
