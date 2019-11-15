package com.tfg.tiendadeelectronica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.tiendadeelectronica.model.Subcategoria;
import com.tfg.tiendadeelectronica.repository.SubcategoriaRepository;
import com.tfg.tiendadeelectronica.service.SubcategoriaService;
import com.tfg.tiendadeelectronica.utils.Constantes;

@Service
@Transactional
public class SubcategoriaServiceImpl implements SubcategoriaService{

	@Autowired
	private SubcategoriaRepository subcategoriaRepository;

	@Override
	public List<Subcategoria> listarSubcategorias(){
		return subcategoriaRepository.findAll();
	}
	
	@Override
	public Subcategoria obtenerSubcategoria(Long idSubcategoria) {
		return subcategoriaRepository.getOne(idSubcategoria);
	}
	
	@Override
	public void guardarSubcategoria(Subcategoria subcategoria) {
		subcategoriaRepository.save(subcategoria);
	}
	
	@Override
	public void actualizarSubcategoria(Subcategoria subcategoria) throws Exception {
		
			if(subcategoria.getIdSubcategoria() != null) {
				subcategoriaRepository.save(subcategoria);
			}else {
				throw new Exception(Constantes.SUBCATEGORIA_NO_VALIDA);
			}
	}
	
	@Override
	public void eliminarSubcategoria(Long subcategoriaId) {
		subcategoriaRepository.deleteById(subcategoriaId);
	}
	
	@Override
	public boolean existePorIdSubcategoria(Long idSubcategoria) {
		return subcategoriaRepository.existsById(idSubcategoria);
	}
	
	@Override
	public boolean existePorNombreSubcategoria(String nombreSubcategoria) {
		return subcategoriaRepository.existsByNombreSubcategoria(nombreSubcategoria);
	}
}
