package com.tfg.tiendadeelectronica.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "ARTICULOS")
@Inheritance(strategy=InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Articulo implements Serializable{
	
	private static final long serialVersionUID = -8538182987284338830L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_ARTICULO")
	private Long idArticulo;
	
	@Column(name = "NOMBRE_ARTICULO", unique = true)
	@NotNull
	private String nombreArticulo;
	
	@Column(name = "PRECIO")
	@NotNull
	private Double precio;
	
	@Column(name = "PRECIO_IVA")
	@NotNull
	private Double precioIVA;
	
	@Column(name = "CANTIDAD")
	@NotNull
	private Integer cantidad = 0;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "CARACTERISTICAS")
	private String caracteristicas;
	
	@Column(name = "ESPECIFICACIONES_FABRICANTE")
	private String especificacionesFabricante;
	
	@Column(name = "FECHA_ALTA")
	private Timestamp fechaAlta;
	
	@Column(name = "FABRICANTE")
	@NotNull
	private String fabricante;
	
	@Lob
	@Column(name = "IMAGEN")
	private byte[] imagen;
	
	@ManyToOne
	@JoinColumn(name="idSubcategoria")
	private Subcategoria subcategoria;
}
