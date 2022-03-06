package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.entity.Coche;

public interface CocheService {

	public List<Coche> findAll();
	
	public Coche findById(Long id);
	
	public Coche save(Coche coche);
	
	public void delete(Long id);
	
}
