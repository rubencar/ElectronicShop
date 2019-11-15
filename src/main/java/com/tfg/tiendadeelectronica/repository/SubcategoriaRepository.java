package com.tfg.tiendadeelectronica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.tiendadeelectronica.model.Subcategoria;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {

	Optional<Subcategoria> findByNombreSubcategoria(String nombreSubategoria);
	
	boolean existsByNombreSubcategoria(String nombreSubategoria);
	
}
