package com.formacionspring.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.apirest.entity.Modelo;

@Repository
public interface ModeloDao extends CrudRepository<Modelo, Long> {

}
