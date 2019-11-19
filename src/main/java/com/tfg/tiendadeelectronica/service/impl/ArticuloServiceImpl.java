package com.tfg.tiendadeelectronica.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.tiendadeelectronica.model.Articulo;
import com.tfg.tiendadeelectronica.repository.ArticuloRepository;
import com.tfg.tiendadeelectronica.service.ArticuloService;
import com.tfg.tiendadeelectronica.utils.Constantes;

@Service
@Transactional
public class ArticuloServiceImpl implements ArticuloService{

	@Autowired
	private ArticuloRepository articuloRepository;
	
	@Override
	public void guardarArticulo(Articulo articulo) {
		articuloRepository.save(articulo);		
	}

	@Override
	public void actualizarArticulo(Articulo articulo) throws Exception {
		if(articulo.getIdArticulo() != null) {
			articuloRepository.save(articulo);
		}else {
			throw new Exception(Constantes.ARTICULO_NO_VALIDO);
		}		
	}

	@Override
	public void eliminarArticulo(Long idArticulo) {
		articuloRepository.deleteById(idArticulo);		
	}

	@Override
	public Articulo obtenerArticulo(Long idArticulo) {
		return articuloRepository.getOne(idArticulo);
	}

	@Override
	public Optional<Articulo> obtenerPorNombreArticulo(String nombreArticulo) {
		return articuloRepository.findByNombreArticulo(nombreArticulo);
	}

	@Override
	public List<Articulo> listarArticulosPorFabricante(String fabricante) {
		return articuloRepository.findByFabricanteOrderByNombreArticuloAsc(fabricante);
	}

	@Override
	public List<Articulo> listarArticulos() {
		return articuloRepository.findAllByOrderByNombreArticuloAsc();
	}

	@Override
	public boolean existePorNombreArticulo(String nombreArticulo) {
		return articuloRepository.existsByNombreArticulo(nombreArticulo);
	}

	@Override
	public boolean existePorIdArticulo(Long idArticulo) {
		return articuloRepository.existsById(idArticulo);
	}
	
	@Override
	public String comprobarValoresNoNulos(Articulo articulo) {
		
		if(StringUtils.isBlank(articulo.getNombreArticulo())){
			return Constantes.NOMBRE_USUARIO_OBLIGATORIO;
		}
		
		return "";
	}

	@Override
	public String existeArticulo(Articulo articulo) {
		if(existePorNombreArticulo(articulo.getNombreArticulo())) {
			return Constantes.NOMBRE_USUARIO_YA_EXISTE;
		}
		return "";
	}

}
