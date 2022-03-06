package com.formacionspring.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.apirest.dao.MarcaDao;
import com.formacionspring.apirest.entity.Marca;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaDao marcaDao;
	
	@Override
	public List<Marca> findAll() {
		return (List<Marca>) marcaDao.findAll();
	}

	@Override
	public Marca findById(Long id) {
		return marcaDao.findById(id).orElse(null);
	}

	@Override
	public Marca save(Marca marca) {
		return marcaDao.save(marca);
	}

	@Override
	public void delete(Long id) {
		marcaDao.deleteById(id);
	}

}
