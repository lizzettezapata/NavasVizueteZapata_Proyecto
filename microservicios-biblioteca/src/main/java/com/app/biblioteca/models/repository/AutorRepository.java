package com.app.biblioteca.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.biblioteca.models.entity.Autor;

public interface AutorRepository extends PagingAndSortingRepository<Autor, Long> {
}
