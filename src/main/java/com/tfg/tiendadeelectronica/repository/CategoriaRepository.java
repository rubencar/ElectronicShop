package com.tfg.tiendadeelectronica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.tiendadeelectronica.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
}
