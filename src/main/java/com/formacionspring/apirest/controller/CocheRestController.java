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

import com.formacionspring.apirest.entity.Coche;
import com.formacionspring.apirest.service.CocheService;

@RestController
@RequestMapping("/api")
public class CocheRestController {

	@Autowired
	private CocheService servicio;
	
	@GetMapping({"/coches","/todos"})
	public List<Coche> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/coches/{id}")
	public ResponseEntity<?>  findCocheById(@PathVariable Long id) {
		Coche coche = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			coche = servicio.findById(id);	
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (coche == null) {
			response.put("mensaje", "El coche ID: " + id.toString() + " no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Coche>(coche, HttpStatus.OK);
	}
	
	@PostMapping("/coches/nuevo")
	public ResponseEntity<?> saveCoche(@RequestBody Coche coche) {
		Coche cocheNuevo = null;
		Map<String, Object> response = new HashMap<>();
		 
		try {
			 cocheNuevo = servicio.save(coche);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 response.put("mensaje", "El coche ha sido creado con éxito!");
		 response.put("coche", cocheNuevo);
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/coches/actualizar/{id}")
	public ResponseEntity<?> updateCoche(@RequestBody Coche coche, @PathVariable Long id) {
		Coche cocheActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (cocheActual == null) {
			response.put("mensaje","Error: no se pudo editar, el coche con ID: " + id.toString() + " no existe.");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			cocheActual.setMarca(coche.getMarca());
			cocheActual.setModelo(coche.getModelo());
			cocheActual.setColor(coche.getColor());
			cocheActual.setMatricula(coche.getMatricula());
			cocheActual.setCilindrada(coche.getCilindrada());
			cocheActual.setVelocidad(coche.getVelocidad());
			
			servicio.save(cocheActual);	
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 response.put("mensaje", "El coche ha sido actualizado con éxito!");
		 response.put("coche", cocheActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/coches/eliminar/{id}")
	public ResponseEntity<?> deleteCoche(@PathVariable Long id) {
		
		Coche cocheActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (cocheActual == null) {
			response.put("mensaje","Error: no se pudo eliminar, el coche con ID: " + id.toString() + " no existe.");
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
		
		response.put("mensaje", "El coche ha sido eliminado con éxito!");
		response.put("coche", cocheActual);
		 
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}




