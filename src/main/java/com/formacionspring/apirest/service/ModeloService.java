package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.entity.Modelo;

public interface ModeloService {

	public List<Modelo> findAll();
	
	public Modelo findById(Long id);
	
	public Modelo save(Modelo modelo);
	
	public void delete(Long id);
	
}
