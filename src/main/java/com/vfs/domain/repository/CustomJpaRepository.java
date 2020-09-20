package com.vfs.domain.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Responsável por centralizar métodos que são de interesse de 
 * várias entidades.
 * 
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

	/**
	 * @see EntityManager#detach(Object)
	 */
	void detach(T entity);
	
}
