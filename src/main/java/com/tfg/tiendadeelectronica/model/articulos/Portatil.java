package com.tfg.tiendadeelectronica.model.articulos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.tfg.tiendadeelectronica.model.Articulo;
import com.tfg.tiendadeelectronica.model.componentesPortatiles.PantallaPortatil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "PORTATILES")
@PrimaryKeyJoinColumn(name="idArticulo")
public class Portatil extends Articulo implements Serializable{

	private static final long serialVersionUID = 8329494863747930375L;
	
	@Column(name = "TAMANIO_PANTALLA")
	private String tamañoPantalla;
	
	@Column(name = "MEMORIA_RAM")
	private String memoriaRAM;
	
	@Column(name = "PROCESADOR")
	private String procesador;
	
	@ManyToOne
	@JoinColumn(name="idPantalla")
	private PantallaPortatil pantalla;
}
