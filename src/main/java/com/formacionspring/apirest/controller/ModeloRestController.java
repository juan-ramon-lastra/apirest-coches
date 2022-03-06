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

import com.formacionspring.apirest.entity.Modelo;
import com.formacionspring.apirest.service.ModeloService;

@RestController
@RequestMapping("/api")
public class ModeloRestController {

	@Autowired
	private ModeloService servicio;
	
	@GetMapping({"/modelos","/todos"})
	public List<Modelo> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/modelos/{id}")
	public ResponseEntity<?>  findModeloById(@PathVariable Long id) {
		Modelo modelo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			modelo = servicio.findById(id);	
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (modelo == null) {
			response.put("mensaje", "El modelo ID: " + id.toString() + " no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Modelo>(modelo, HttpStatus.OK);
	}
	
	@PostMapping("/modelos/nuevo")
	public ResponseEntity<?> saveModelo(@RequestBody Modelo modelo) {
		Modelo modeloNuevo = null;
		Map<String, Object> response = new HashMap<>();
		 
		try {
			 modeloNuevo = servicio.save(modelo);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 response.put("mensaje", "El modelo ha sido creado con éxito!");
		 response.put("modelo", modeloNuevo);
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/modelos/actualizar/{id}")
	public ResponseEntity<?> updateModelo(@RequestBody Modelo modelo, @PathVariable Long id) {
		Modelo modeloActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (modeloActual == null) {
			response.put("mensaje","Error: no se pudo editar, el modelo con ID: " + id.toString() + " no existe.");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			modeloActual.setNombre(modelo.getNombre());
			
			servicio.save(modeloActual);	
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 response.put("mensaje", "El modelo ha sido actualizado con éxito!");
		 response.put("modelo", modeloActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/modelos/eliminar/{id}")
	public ResponseEntity<?> deleteModelo(@PathVariable Long id) {
		
		Modelo modeloActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (modeloActual == null) {
			response.put("mensaje","Error: no se pudo eliminar, el modelo con ID: " + id.toString() + " no existe.");
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
		
		response.put("mensaje", "El modelo ha sido eliminado con éxito!");
		response.put("modelo", modeloActual);
		 
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}




