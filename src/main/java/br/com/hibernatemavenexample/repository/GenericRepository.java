package br.com.hibernatemavenexample.repository;

import java.util.List;

public interface GenericRepository<T> {
	void create(T obj);
	void delete(T obj);
	T find(Long id);
	void update(T obj);
	List<T> findAll();
}
