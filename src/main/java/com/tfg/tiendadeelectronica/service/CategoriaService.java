package com.tfg.tiendadeelectronica.service;

import java.util.List;
import java.util.Optional;

import com.tfg.tiendadeelectronica.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> getCategorias();
	
	public Categoria getCategoria(Long id);

	public void saveCategoria(Categoria categoria);

	public void updateCategoria(Categoria categoria) throws Exception;

	public Categoria deleteCategoria(Long id);
}
