package com.example.demo.services;

import java.util.Optional;

public interface IService<T> {
	Iterable<T> findAll();
	Optional<T> findById(Long id);
	void save(T entity);
	void delete(T entity);
	void delete(Long id);
}
