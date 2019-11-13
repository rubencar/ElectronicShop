package com.tfg.tiendadeelectronica.model.componentesPortatiles;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tfg.tiendadeelectronica.model.articulos.Portatil;

import lombok.Data;

@Data
@Entity
@Table(name = "PANTALLAS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PantallaPortatil implements Serializable{
	
	private static final long serialVersionUID = 2119735827120776171L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_PANTALLA")
	private Long idPantalla;
	
	@Column(name = "TAMANIO")
	private String tamanio;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idPantalla")
	private List<Portatil> portatiles;
}
