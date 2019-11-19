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
import com.tfg.tiendadeelectronica.model.Subcategoria;
import com.tfg.tiendadeelectronica.service.SubcategoriaService;
import com.tfg.tiendadeelectronica.utils.Constantes;

@CrossOrigin(origins = {"http://localhost:4200"}) //Puerto que usará la aplicacion Angular
@RestController 
@RequestMapping("/subcategorias") 
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SubcategoriaController {

	@Autowired	
	private SubcategoriaService subcategoriaService;

	@GetMapping("/listarSubcategorias")
	@ResponseBody
	public ResponseEntity<List<Subcategoria>> listarSubcategorias() {
		List<Subcategoria> listaSubcategorias = subcategoriaService.listarSubcategorias();
		return new ResponseEntity<List<Subcategoria>>(listaSubcategorias, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerSubcategoria/{idSubcategoria}")
	public ResponseEntity<Subcategoria> obtenerSubcategoria(@PathVariable Long idSubcategoria) {
		if(!subcategoriaService.existePorIdSubcategoria(idSubcategoria)) {
			return new ResponseEntity(new Mensaje(Constantes.SUBCATEGORIA_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		Subcategoria subcategoria = subcategoriaService.obtenerSubcategoria(idSubcategoria);
		return new ResponseEntity<Subcategoria>(subcategoria, HttpStatus.OK);
	}
	
	@PostMapping("/guardarSubcategoria")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> guardarSubcategoria(@RequestBody Subcategoria subcategoria) {
		
		if(StringUtils.isBlank(subcategoria.getNombreSubcategoria())){
			return new ResponseEntity(new Mensaje(Constantes.NOMBRE_SUBCATEGORIA_OBLIGATORIO), HttpStatus.BAD_REQUEST);
		}
		
		if(subcategoriaService.existePorNombreSubcategoria(subcategoria.getNombreSubcategoria())) {
			return new ResponseEntity(new Mensaje(Constantes.NOMBRE_SUBCATEGORIA_YA_EXISTE), HttpStatus.BAD_REQUEST);
		}
		
		subcategoriaService.guardarSubcategoria(subcategoria);
		return new ResponseEntity(new Mensaje(Constantes.SUBCATEGORIA_GUARDADA), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarSubcategoria")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> actualizarSubcategoria(@RequestBody Subcategoria subcategoria, @PathVariable("idSubcategoria") Long idSubcategoria) throws Exception {
		
		if(!subcategoriaService.existePorIdSubcategoria(idSubcategoria)) {
			return new ResponseEntity(new Mensaje(Constantes.SUBCATEGORIA_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		
		if(StringUtils.isBlank(subcategoria.getNombreSubcategoria())){
			return new ResponseEntity(new Mensaje(Constantes.NOMBRE_SUBCATEGORIA_OBLIGATORIO), HttpStatus.BAD_REQUEST);
		}
		
		if(subcategoriaService.existePorNombreSubcategoria(subcategoria.getNombreSubcategoria())) {
			return new ResponseEntity(new Mensaje(Constantes.NOMBRE_SUBCATEGORIA_YA_EXISTE), HttpStatus.BAD_REQUEST);
		}
		
		Subcategoria subcategoriaUpdate = subcategoriaService.obtenerSubcategoria(idSubcategoria);
		subcategoriaUpdate.setNombreSubcategoria(subcategoria.getNombreSubcategoria());
		subcategoriaUpdate.setDescripcion(subcategoria.getDescripcion());
		subcategoriaService.guardarSubcategoria(subcategoria);
		return new ResponseEntity(new Mensaje(Constantes.SUBCATEGORIA_ACTUALIZADA), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/eliminarSubcategoria/{idSubcategoria}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> eliminarSubcategoria(@PathVariable Long idSubcategoria) {
		if(!subcategoriaService.existePorIdSubcategoria(idSubcategoria)) {
			return new ResponseEntity(new Mensaje(Constantes.SUBCATEGORIA_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		subcategoriaService.eliminarSubcategoria(idSubcategoria);
		return new ResponseEntity(new Mensaje(Constantes.SUBCATEGORIA_ELIMINADA), HttpStatus.OK);
	}
	
}
