package com.tfg.tiendadeelectronica.service;

import java.util.List;

import com.tfg.tiendadeelectronica.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> listarCategorias();
	
	public Categoria obtenerCategoria(Long id);

	public void guardarCategoria(Categoria categoria);

	public void actualizarCategoria(Categoria categoria) throws Exception;

	public void eliminarCategoria(Long id);
	
	public boolean existePorIdCategoria(Long idCategoria);
	
	public boolean existePorNombreCategoria(String nombreCategoria);
}
