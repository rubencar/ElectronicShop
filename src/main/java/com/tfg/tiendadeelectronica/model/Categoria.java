package com.tfg.tiendadeelectronica.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORIAS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 7393363725992245860L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_CATEGORIA")
	private Long idCategoria;
	
	@Column(name = "NOMBRE_CATEGORIA", unique = true)
	@NotBlank
	private String nombreCategoria;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idCategoria")
	private List<Subcategoria> subcategorias;

}
