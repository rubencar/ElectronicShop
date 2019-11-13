package com.tfg.tiendadeelectronica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tfg.tiendadeelectronica.enums.RolNombre;

import lombok.Data;

@Data
@Entity
@Table(name = "ROLES")
public class Rol implements Serializable{
	
	private static final long serialVersionUID = 6770942730370547854L;

	@Id
	@Column(name = "ID_ROL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRol;
	
	@Enumerated(EnumType.STRING) //para indicar que se trata de un enum tipo String
	@Column(name = "NOMBRE_ROL")
    @NotNull
	private RolNombre rolNombre;
	
	public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
    
    
}
