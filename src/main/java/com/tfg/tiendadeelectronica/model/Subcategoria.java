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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "SUBCATEGORIAS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subcategoria implements Serializable{

	private static final long serialVersionUID = -3105071576216724548L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_SUBCATEGORIA")
	private Long idSubcategoria;
	
	@Column(name = "NOMBRE_SUBCATEGORIA")
	private String nombreSubcategoria;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idSubcategoria")
	private List<Articulo> articulos;
}
