package com.tfg.tiendadeelectronica.service;

import java.util.List;

import com.tfg.tiendadeelectronica.model.Subcategoria;

public interface SubcategoriaService {

	public List<Subcategoria> listarSubcategorias();
	
	public Subcategoria obtenerSubcategoria(Long id);

	public void guardarSubcategoria(Subcategoria subcategoria);

	public void actualizarSubcategoria(Subcategoria subcategoria) throws Exception;

	public void eliminarSubcategoria(Long id);
	
	public boolean existePorIdSubcategoria(Long idSubcategoria);
	
	public boolean existePorNombreSubcategoria(String nombreSubcategoria);
	
}
