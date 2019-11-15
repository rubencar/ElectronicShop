package com.tfg.tiendadeelectronica.controller;

import java.util.List;
import java.util.Optional;

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
import com.tfg.tiendadeelectronica.model.Usuario;
import com.tfg.tiendadeelectronica.service.UsuarioService;
import com.tfg.tiendadeelectronica.utils.Constantes;

@CrossOrigin(origins = {"http://localhost:4200"}) //Puerto que usará la aplicacion Angular
@RestController 
@RequestMapping("/usuario") 
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UsuarioController {
	
	@Autowired	
	private UsuarioService usuarioService;
	
	@GetMapping("/listarUsuarios")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
		return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerUsuario/{idUsuario}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long idUsuario) {
		if(!usuarioService.existePorIdUsuario(idUsuario)) {
			return new ResponseEntity(new Mensaje(Constantes.USUARIO_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerUsuarioPorDni/{dni}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Optional<Usuario>> obtenerUsuarioPorDni(@PathVariable String dni) {
		if(!usuarioService.existePorDni(dni)) {
			return new ResponseEntity(new Mensaje(Constantes.USUARIO_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		Optional<Usuario> usuario = usuarioService.obtenerPorDni(dni);
		return new ResponseEntity<Optional<Usuario>>(usuario, HttpStatus.OK);
	}
	
	@PostMapping("/guardarUsuario")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) {
		
		String mensajeValidaciones = usuarioService.comprobarValoresNoNulos(usuario);
		if(!StringUtils.isEmpty(mensajeValidaciones)){
			return new ResponseEntity(new Mensaje(mensajeValidaciones), HttpStatus.BAD_REQUEST);
		}
		
		mensajeValidaciones = usuarioService.existeUsuario(usuario);
		if(!StringUtils.isEmpty(mensajeValidaciones)){
			return new ResponseEntity(new Mensaje(mensajeValidaciones), HttpStatus.BAD_REQUEST);
		}
		
		usuarioService.guardarUsuario(usuario);
		
		return new ResponseEntity(new Mensaje(Constantes.USUARIO_GUARDADO), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarUsuario")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable("idUsuario") Long idUsuario) throws Exception {
		
		if(!usuarioService.existePorIdUsuario(idUsuario)) {
			return new ResponseEntity(new Mensaje(Constantes.USUARIO_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		
		String mensajeValidaciones = usuarioService.comprobarValoresNoNulos(usuario);
		if(!StringUtils.isEmpty(mensajeValidaciones)){
			return new ResponseEntity(new Mensaje(mensajeValidaciones), HttpStatus.BAD_REQUEST);
		}
		
		mensajeValidaciones = usuarioService.existeUsuario(usuario);
		if(!StringUtils.isEmpty(mensajeValidaciones)){
			return new ResponseEntity(new Mensaje(mensajeValidaciones), HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuarioUpdate = usuarioService.obtenerUsuario(idUsuario);
		usuarioUpdate.setNombreUsuario(usuario.getNombreUsuario());
		usuarioUpdate.setNombre(usuario.getNombre());
		
		return new ResponseEntity(new Mensaje(Constantes.USUARIO_ACTUALIZADO), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/eliminarUsuario/{idUsuario}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long idUsuario) {
		if(!usuarioService.existePorIdUsuario(idUsuario)) {
			return new ResponseEntity(new Mensaje(Constantes.USUARIO_NO_EXISTE), HttpStatus.NOT_FOUND);
		}
		usuarioService.eliminarUsuario(idUsuario);
		return new ResponseEntity(new Mensaje(Constantes.USUARIO_ELIMINADO), HttpStatus.OK);
	}
}
