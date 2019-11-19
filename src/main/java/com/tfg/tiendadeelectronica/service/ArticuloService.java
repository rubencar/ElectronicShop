package com.tfg.tiendadeelectronica.service;

import java.util.List;
import java.util.Optional;

import com.tfg.tiendadeelectronica.model.Articulo;

public interface ArticuloService {

	public void guardarArticulo(Articulo articulo);
	public void actualizarArticulo(Articulo articulo) throws Exception;
	public void eliminarArticulo(Long idArticulo);
	public Articulo obtenerArticulo(Long idArticulo);
	public Optional<Articulo> obtenerPorNombreArticulo(String nombreArticulo);
	public List<Articulo> listarArticulosPorFabricante(String fabricante);
	public List<Articulo> listarArticulos();
	public boolean existePorNombreArticulo(String nombreArticulo);
	public boolean existePorIdArticulo(Long idArticulo);
	public String comprobarValoresNoNulos(Articulo articulo);
	public String existeArticulo(Articulo articulo);
}
