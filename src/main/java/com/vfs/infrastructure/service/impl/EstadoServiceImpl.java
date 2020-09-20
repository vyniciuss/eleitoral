package com.vfs.infrastructure.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vfs.domain.exception.EntidadeEmUsoException;
import com.vfs.domain.exception.EstadoNaoEncontradoException;
import com.vfs.domain.model.Estado;
import com.vfs.domain.repository.EstadoRepository;
import com.vfs.domain.service.EstadoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EstadoServiceImpl implements EstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

	private final EstadoRepository estadoRepository;

	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Transactional
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			estadoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

	@Override
	public Estado findOrFail(Long id) {
		return estadoRepository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(id));
	}

}
