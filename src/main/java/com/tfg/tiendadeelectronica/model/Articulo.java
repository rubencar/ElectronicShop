package com.tfg.tiendadeelectronica.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column(name = "NOMBRE_ARTICULO")
	private String nombreArticulo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "PRECIO")
	private Double precio;
	
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@ManyToOne
	@JoinColumn(name="idSubcategoria")
	private Subcategoria subcategoria;
}
