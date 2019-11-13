package com.tfg.tiendadeelectronica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.tiendadeelectronica.enums.RolNombre;
import com.tfg.tiendadeelectronica.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
