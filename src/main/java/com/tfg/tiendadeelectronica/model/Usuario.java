package com.tfg.tiendadeelectronica.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -7473600451938186594L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
    private Long idUsuario;

    @NotNull
    @Column(name = "NOMBRE")
    private String nombre;
    
    @NotNull
    @Column(name = "APELLIDOS")
    private String apellidos;
    
    @NotNull
    @Column(name = "DNI", unique = true)
    private String dni;
    
    @NotNull
    @Column(name = "TELEFONO1", unique = true)
    private String telefono1;
    
    @Column(name = "TELEFONO2")
    private String telefono2;
    
    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotNull
    @Column(name = "NOMBRE_USUARIO", unique = true)
    private String nombreUsuario;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;
    
    @NotNull
    @ManyToMany
    @JoinTable(name = "USUARIOS_ROLES", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idRol"))
    private Set<Rol> roles = new HashSet<>();
    
    public Usuario() {
    }

	public Usuario(@NotNull String nombre, @NotNull String apellidos, @NotNull String dni, @NotNull String telefono1,
			String telefono2, @NotNull String email, @NotNull String nombreUsuario, @NotNull String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

    
}
