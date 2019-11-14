package com.tfg.tiendadeelectronica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.tiendadeelectronica.model.Categoria;
import com.tfg.tiendadeelectronica.repository.CategoriaRepository;
import com.tfg.tiendadeelectronica.service.CategoriaService;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository categoriaDao;

//	@Transactional(readOnly = true)
	@Override
	public List<Categoria> listarCategorias(){
		return categoriaDao.findAll();
	}
	
//	@Transactional(readOnly = true)
	@Override
	public Categoria obtenerCategoria(Long idCategoria) {
		return categoriaDao.getOne(idCategoria);
	}
	
//	@Transactional
	@Override
	public void guardarCategoria(Categoria categoria) {
		categoriaDao.save(categoria);
	}
	
//	@Transactional
	@Override
	public void actualizarCategoria(Categoria categoria) throws Exception {
//		Categoria categoriaUpdate = categoriaDao.getOne(categoria.getId());
	
			if(categoria.getIdCategoria() != null) {
				categoriaDao.save(categoria);
			}else {
				throw new Exception("Categoría no válida.");
			}
	}
	
//	@Transactional
	@Override
	public void eliminarCategoria(Long categoriaId) {
		categoriaDao.deleteById(categoriaId);
	}
	
//	@Transactional
	@Override
	public boolean existePorIdCategoria(Long idCategoria) {
		return categoriaDao.existsById(idCategoria);
	}
	
	@Override
	public boolean existePorNombreCategoria(String nombreCategoria) {
		return categoriaDao.existsByNombreCategoria(nombreCategoria);
	}
}
