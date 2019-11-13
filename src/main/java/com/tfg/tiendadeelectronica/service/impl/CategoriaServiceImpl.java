package com.tfg.tiendadeelectronica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.tiendadeelectronica.model.Categoria;
import com.tfg.tiendadeelectronica.repository.CategoriaRepository;
import com.tfg.tiendadeelectronica.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository categoriaDao;

	@Transactional(readOnly = true)
	@Override
	public List<Categoria> getCategorias(){
		return categoriaDao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Categoria getCategoria(Long idCategoria) {
		return categoriaDao.getOne(idCategoria);
	}
	
	@Transactional
	@Override
	public void saveCategoria(Categoria categoria) {
		categoriaDao.save(categoria);
	}
	
	@Transactional
	@Override
	public void updateCategoria(Categoria categoria) throws Exception {
//		Categoria categoriaUpdate = categoriaDao.getOne(categoria.getId());
	
			if(categoria.getIdCategoria() != null) {
				categoriaDao.save(categoria);
			}else {
				throw new Exception("Categoría no válida.");
			}
	}
	
	@Transactional
	@Override
	public Categoria deleteCategoria(Long categoriaId) {
		Categoria categoria = categoriaDao.getOne(categoriaId);
		categoriaDao.delete(categoria);
		return categoria;
	}
}
