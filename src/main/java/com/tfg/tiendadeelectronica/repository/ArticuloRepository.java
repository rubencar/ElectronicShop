package com.tfg.tiendadeelectronica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.tiendadeelectronica.model.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long>{

	Optional<Articulo> findByNombreArticulo(String nombreArticulo);
	
	List<Articulo> findByFabricanteOrderByNombreArticuloAsc(String fabricante);
	
	List<Articulo> findAllByOrderByNombreArticuloAsc();
	
	boolean existsByNombreArticulo(String nombreArticulo);
}
