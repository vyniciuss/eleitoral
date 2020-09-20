package com.vfs.domain.service;

import com.vfs.domain.model.Estado;

public interface EstadoService extends BaseService<Estado, Long> {

	public Estado salvar(Estado estado);

	public void excluir(Long estadoId);

}
