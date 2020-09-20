package com.vfs.domain.service;

/**
 * Responsável por centralizar métodos de serviço que são de interesse de 
 * várias entidades.
 * 
 * @param <T> entidade
 * @param <ID> identificador
 */
public interface BaseService<T, ID> {

	public T findOrFail(ID id);


}
