package com.formacionspring.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.apirest.entity.Marca;
import com.formacionspring.apirest.service.MarcaService;

@RestController
@RequestMapping("/api")
public class MarcaRestController {

	@Autowired
	private MarcaService servicio;
	
	@GetMapping({"/marcas","/todas"})
	public List<Marca> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/marcas/{id}")
	public ResponseEntity<?>  findMarcaById(@PathVariable Long id) {
		Marca marca = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			marca = servicio.findById(id);	
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (marca == null) {
			response.put("mensaje", "La marca ID: " + id.toString() + " no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Marca>(marca, HttpStatus.OK);
	}
	
	@PostMapping("/marcas/nueva")
	public ResponseEntity<?> saveMarca(@RequestBody Marca marca) {
		Marca marcaNueva = null;
		Map<String, Object> response = new HashMap<>();
		 
		try {
			 marcaNueva = servicio.save(marca);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		response.put("mensaje", "La marca ha sido creada con éxito!");
		response.put("marca", marcaNueva);
		 
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/marcas/actualizar/{id}")
	public ResponseEntity<?> updateMarca(@RequestBody Marca marca, @PathVariable Long id) {
		Marca marcaActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (marcaActual == null) {
			response.put("mensaje","Error: no se pudo editar, la marca con ID: " + id.toString() + " no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			marcaActual.setNombre(marca.getNombre());
			marcaActual.setLogotipo(marca.getLogotipo());
			
			servicio.save(marcaActual);	
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 response.put("mensaje", "La marca ha sido actualizada con éxito!");
		 response.put("marca", marcaActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/marcas/eliminar/{id}")
	public ResponseEntity<?> deleteMarca(@PathVariable Long id) {
		
		Marca marcaDeleted = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (marcaDeleted == null) {
			response.put("mensaje","Error: no se pudo eliminar, la marca con ID: " + id.toString() + " no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			servicio.delete(id);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un delete a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La marca ha sido eliminada con éxito!");
		response.put("marca", marcaDeleted);
		 
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}




