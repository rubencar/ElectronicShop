package com.tfg.tiendadeelectronica.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.tfg.tiendadeelectronica.model.Articulo;
import com.tfg.tiendadeelectronica.service.ArticuloService;
import com.tfg.tiendadeelectronica.utils.Constantes;
import com.tfg.tiendadeelectronica.utils.TiendaDeElectronicaUtils;

@RestController
@RequestMapping("/articulos")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ArticuloController {

	@Autowired
	private ArticuloService articuloService;
	
	@GetMapping("/listarArticulos")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Articulo>> listarArticulos() {
		List<Articulo> listaArticulos = articuloService.listarArticulos();
		return new ResponseEntity<List<Articulo>>(listaArticulos, HttpStatus.OK);
	}
	
	@GetMapping("/listarArticulosPorFabricante/{fabricante}")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Articulo>> listarArticulosPorFabricante(@PathVariable String fabricante) {
		List<Articulo> listaArticulos = articuloService.listarArticulosPorFabricante(fabricante);
		return new ResponseEntity<List<Articulo>>(listaArticulos, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerArticulo/{idArticulo}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Articulo> obtenerArticulo(@PathVariable Long idArticulo) {
		if(!articuloService.existePorIdArticulo(idArticulo)) {
			return new ResponseEntity(new Mensaje(Constantes.ARTICULO_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		Articulo articulo = articuloService.obtenerArticulo(idArticulo);
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}
	
	@PostMapping("/guardarArticulo")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> guardarArticulo(@RequestBody Articulo articulo) {
		
		String mensajeValidaciones = articuloService.comprobarValoresNoNulos(articulo);
		if(!StringUtils.isEmpty(mensajeValidaciones)){
			return new ResponseEntity(new Mensaje(mensajeValidaciones), HttpStatus.BAD_REQUEST);
		}
		
		mensajeValidaciones = articuloService.existeArticulo(articulo);
		if(!StringUtils.isEmpty(mensajeValidaciones)){
			return new ResponseEntity(new Mensaje(mensajeValidaciones), HttpStatus.BAD_REQUEST);
		}
		
		if(articulo.getPrecio() >= 0) {
			articulo.setPrecioIVA(TiendaDeElectronicaUtils.obtenerPrecioConIVA(articulo.getPrecio()));
		}else {
			return new ResponseEntity(new Mensaje(Constantes.PRECIO_NO_VALIDO), HttpStatus.BAD_REQUEST);
		}
		
		articuloService.guardarArticulo(articulo);
		
		return new ResponseEntity(new Mensaje(Constantes.ARTICULO_GUARDADO), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarArticulo")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> actualizarArticulo(@RequestBody Articulo articulo, @PathVariable("idArticulo") Long idArticulo) throws Exception {
		
		if(!articuloService.existePorIdArticulo(idArticulo)) {
			return new ResponseEntity(new Mensaje(Constantes.ARTICULO_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		
		String mensajeValidaciones = articuloService.comprobarValoresNoNulos(articulo);
		if(!StringUtils.isEmpty(mensajeValidaciones)){
			return new ResponseEntity(new Mensaje(mensajeValidaciones), HttpStatus.BAD_REQUEST);
		}
		
		mensajeValidaciones = articuloService.existeArticulo(articulo);
		if(!StringUtils.isEmpty(mensajeValidaciones)){
			return new ResponseEntity(new Mensaje(mensajeValidaciones), HttpStatus.BAD_REQUEST);
		}
		
		Articulo articuloUpdate = articuloService.obtenerArticulo(idArticulo);
		articuloUpdate.setNombreArticulo(articulo.getNombreArticulo());
		if(articulo.getPrecio() >= 0) {
			articulo.setPrecio(articulo.getPrecio());
			articulo.setPrecioIVA(TiendaDeElectronicaUtils.obtenerPrecioConIVA(articulo.getPrecio()));
		}else {
			return new ResponseEntity(new Mensaje(Constantes.PRECIO_NO_VALIDO), HttpStatus.BAD_REQUEST);
		}
		articuloUpdate.setDescripcion(articulo.getDescripcion());
		articuloUpdate.setCaracteristicas(articulo.getCaracteristicas());
		articuloUpdate.setEspecificacionesFabricante(articulo.getEspecificacionesFabricante());
		
		return new ResponseEntity(new Mensaje(Constantes.ARTICULO_ACTUALIZADO), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/eliminarArticulo/{idArticulo}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> eliminarArticulo(@PathVariable Long idArticulo) {
		if(!articuloService.existePorIdArticulo(idArticulo)) {
			return new ResponseEntity(new Mensaje(Constantes.ARTICULO_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		articuloService.eliminarArticulo(idArticulo);
		return new ResponseEntity(new Mensaje(Constantes.ARTICULO_ELIMINADO), HttpStatus.OK);
	}
}
