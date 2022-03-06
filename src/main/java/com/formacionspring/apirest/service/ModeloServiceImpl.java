package com.formacionspring.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.apirest.dao.ModeloDao;
import com.formacionspring.apirest.entity.Modelo;

@Service
public class ModeloServiceImpl implements ModeloService {

	@Autowired
	private ModeloDao modeloDao;
	
	@Override
	public List<Modelo> findAll() {
		return (List<Modelo>) modeloDao.findAll();
	}

	@Override
	public Modelo findById(Long id) {
		return modeloDao.findById(id).orElse(null);
	}

	@Override
	public Modelo save(Modelo modelo) {
		return modeloDao.save(modelo);
	}

	@Override
	public void delete(Long id) {
		modeloDao.deleteById(id);
	}

}
