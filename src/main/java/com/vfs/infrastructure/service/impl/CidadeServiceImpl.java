package com.vfs.infrastructure.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vfs.domain.exception.CidadeNaoEncontradaException;
import com.vfs.domain.exception.EntidadeEmUsoException;
import com.vfs.domain.model.Cidade;
import com.vfs.domain.model.Estado;
import com.vfs.domain.repository.CidadeRepository;
import com.vfs.domain.service.CidadeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CidadeServiceImpl implements CidadeService {

	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";
	private final EstadoServiceImpl estadoService;
	private final CidadeRepository cidadeRepository;

	@Transactional
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();

		Estado estado = estadoService.findOrFail(estadoId);

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	@Transactional
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	@Override
	public Cidade findOrFail(Long id) {
		return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}

}
