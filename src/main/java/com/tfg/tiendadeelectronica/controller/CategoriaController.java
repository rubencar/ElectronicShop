package com.tfg.tiendadeelectronica.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.tiendadeelectronica.DTO.Mensaje;
import com.tfg.tiendadeelectronica.model.Categoria;
import com.tfg.tiendadeelectronica.service.CategoriaService;
import com.tfg.tiendadeelectronica.utils.Constantes;

@CrossOrigin(origins = {"http://localhost:4200"}) //Puerto que usará la aplicacion Angular
@RestController 
@RequestMapping("/categoria") 
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CategoriaController {	
	
	@Autowired	
	private CategoriaService categoriaService;

	@GetMapping("/listarCategorias")
	@ResponseBody
	public ResponseEntity<List<Categoria>> listarCategorias() {
		List<Categoria> listaCategorias = categoriaService.listarCategorias();
		return new ResponseEntity<List<Categoria>>(listaCategorias, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerCategoria/{idCategoria}")
	public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Long idCategoria) {
		if(!categoriaService.existePorIdCategoria(idCategoria)) {
			return new ResponseEntity(new Mensaje(Constantes.CATEGORIA_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}
	
	@PostMapping("/guardarCategoria")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> guardarCategoria(@RequestBody Categoria categoria) {
		
		if(StringUtils.isBlank(categoria.getNombreCategoria())){
			return new ResponseEntity(new Mensaje(Constantes.NOMBRE_CATEGORIA_OBLIGATORIO), HttpStatus.BAD_REQUEST);
		}
		
		if(categoriaService.existePorNombreCategoria(categoria.getNombreCategoria())) {
			return new ResponseEntity(new Mensaje(Constantes.NOMBRE_CATEGORIA_YA_EXISTE), HttpStatus.BAD_REQUEST);
		}
		
//		categoria.setIdCategoria(null);
		categoriaService.guardarCategoria(categoria);
		return new ResponseEntity(new Mensaje(Constantes.CATEGORIA_GUARDADA), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarCategoria")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> actualizarCategoria(@RequestBody Categoria categoria, @PathVariable("idCategoria") Long idCategoria) throws Exception {
		
		if(!categoriaService.existePorIdCategoria(idCategoria)) {
			return new ResponseEntity(new Mensaje(Constantes.CATEGORIA_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		
		if(StringUtils.isBlank(categoria.getNombreCategoria())){
			return new ResponseEntity(new Mensaje(Constantes.NOMBRE_CATEGORIA_OBLIGATORIO), HttpStatus.BAD_REQUEST);
		}
		
		if(categoriaService.existePorNombreCategoria(categoria.getNombreCategoria())) {
			return new ResponseEntity(new Mensaje(Constantes.NOMBRE_CATEGORIA_YA_EXISTE), HttpStatus.BAD_REQUEST);
		}
		
		Categoria categoriaUpdate = categoriaService.obtenerCategoria(idCategoria);
		categoriaUpdate.setNombreCategoria(categoria.getNombreCategoria());
		categoriaUpdate.setDescripcion(categoria.getDescripcion());
		categoriaService.guardarCategoria(categoria);
		return new ResponseEntity(new Mensaje(Constantes.CATEGORIA_ACTUALIZADA), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/eliminarCategoria/{idCategoria}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> eliminarCategoria(@PathVariable Long idCategoria) {
		if(!categoriaService.existePorIdCategoria(idCategoria)) {
			return new ResponseEntity(new Mensaje(Constantes.CATEGORIA_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		categoriaService.eliminarCategoria(idCategoria);
		return new ResponseEntity(new Mensaje(Constantes.CATEGORIA_ELIMINADA), HttpStatus.OK);
	}

}
