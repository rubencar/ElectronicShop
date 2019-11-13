package com.tfg.tiendadeelectronica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.tfg.tiendadeelectronica.model.Categoria;
import com.tfg.tiendadeelectronica.service.CategoriaService;

@CrossOrigin(origins = {"http://localhost:4200"}) //Puerto que usará la aplicacion Angular
@RestController 
@RequestMapping("/categoria") 
public class CategoriaController {	
	
	@Autowired	
	private CategoriaService categoriaService;

	@GetMapping("/getCategorias")
	@ResponseBody
	public List<Categoria> getCategorias() {
		return categoriaService.getCategorias();
	}
	
	@GetMapping("/getCategoria/{categoriaId}")
	public Categoria getCategoria(@PathVariable Long categoriaId) {
		Categoria categoria = categoriaService.getCategoria(categoriaId);
		return categoria;
	}
	
	@PostMapping("/saveCategoria")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
	public Categoria saveCategoria(@RequestBody Categoria categoria) {
		categoria.setIdCategoria(null);
		categoriaService.saveCategoria(categoria);
		return categoria;
	}
	
	@PutMapping("/updateCategoria")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public Categoria updateCategoria(@RequestBody Categoria categoria) throws Exception {
		categoriaService.updateCategoria(categoria);
		return categoria;
	}
	
	@DeleteMapping("/deleteCategoria/{categoriaId}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public Categoria deleteCategoria(@PathVariable Long categoriaId) {
		Categoria categoria = categoriaService.deleteCategoria(categoriaId);
		return categoria;
	}

}
