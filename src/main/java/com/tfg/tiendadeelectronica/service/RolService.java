package com.tfg.tiendadeelectronica.service;

import java.util.Optional;

import com.tfg.tiendadeelectronica.enums.RolNombre;
import com.tfg.tiendadeelectronica.model.Rol;

/**
 * @author rcarreterodiaz
 *
 */
public interface RolService {

	/**
	 * @param rolNombre
	 * @return
	 */
	public Optional<Rol> getByRolNombre(RolNombre rolNombre);
}
