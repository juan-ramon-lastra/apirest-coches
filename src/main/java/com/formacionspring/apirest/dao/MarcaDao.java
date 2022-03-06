package com.formacionspring.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.apirest.entity.Marca;

@Repository
public interface MarcaDao extends CrudRepository<Marca, Long> {

}
