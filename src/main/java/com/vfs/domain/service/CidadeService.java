package com.vfs.domain.service;

import com.vfs.domain.model.Cidade;

public interface CidadeService extends BaseService<Cidade, Long>{

	public Cidade salvar(Cidade cidade) ;

	public void excluir(Long cidadeId) ;

}
