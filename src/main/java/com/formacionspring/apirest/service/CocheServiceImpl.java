package com.formacionspring.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.apirest.dao.CocheDao;
import com.formacionspring.apirest.entity.Coche;

@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheDao cocheDao;
	
	@Override
	public List<Coche> findAll() {
		return (List<Coche>) cocheDao.findAll();
	}

	@Override
	public Coche findById(Long id) {
		return cocheDao.findById(id).orElse(null);
	}

	@Override
	public Coche save(Coche coche) {
		return cocheDao.save(coche);
	}

	@Override
	public void delete(Long id) {
		cocheDao.deleteById(id);
	}

}
