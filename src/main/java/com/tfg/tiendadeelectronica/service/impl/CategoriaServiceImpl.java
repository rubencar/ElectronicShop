package com.tfg.tiendadeelectronica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.tiendadeelectronica.model.Categoria;
import com.tfg.tiendadeelectronica.repository.CategoriaRepository;
import com.tfg.tiendadeelectronica.service.CategoriaService;
import com.tfg.tiendadeelectronica.utils.Constantes;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> listarCategorias(){
		return categoriaRepository.findAll();
	}
	
	@Override
	public Categoria obtenerCategoria(Long idCategoria) {
		return categoriaRepository.getOne(idCategoria);
	}
	
	@Override
	public void guardarCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
	}
	
	@Override
	public void actualizarCategoria(Categoria categoria) throws Exception {
		
			if(categoria.getIdCategoria() != null) {
				categoriaRepository.save(categoria);
			}else {
				throw new Exception(Constantes.CATEGORIA_NO_VALIDA);
			}
	}
	
	@Override
	public void eliminarCategoria(Long categoriaId) {
		categoriaRepository.deleteById(categoriaId);
	}
	
	@Override
	public boolean existePorIdCategoria(Long idCategoria) {
		return categoriaRepository.existsById(idCategoria);
	}
	
	@Override
	public boolean existePorNombreCategoria(String nombreCategoria) {
		return categoriaRepository.existsByNombreCategoria(nombreCategoria);
	}
}
