package com.vfs.api.v1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vfs.api.v1.assembler.CidadeModelAssembler;
import com.vfs.api.v1.assembler.DisassemblerBase;
import com.vfs.api.v1.document.controller.CidadeControllerDoc;
import com.vfs.api.v1.model.CidadeModel;
import com.vfs.api.v1.model.input.CidadeInput;
import com.vfs.domain.exception.EstadoNaoEncontradoException;
import com.vfs.domain.exception.NegocioException;
import com.vfs.domain.model.Cidade;
import com.vfs.domain.model.Estado;
import com.vfs.domain.repository.CidadeRepository;
import com.vfs.domain.service.CidadeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerDoc {

	private final CidadeRepository cidadeRepository;
	private final CidadeService cadastroCidade;
	private final CidadeModelAssembler cidadeModelAssembler;
	private final DisassemblerBase cidadeInputDisassembler;

	@Override
	@GetMapping
	public CollectionModel<CidadeModel> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		return cidadeModelAssembler.toCollectionModel(todasCidades);
	}

	@Override
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.findOrFail(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput, Cidade.class);
			cidade = cadastroCidade.salvar(cidade);
			CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
		try {
			
			Cidade cidadeAtual = cadastroCidade.findOrFail(cidadeId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("estado", Estado.builder().build());
			System.out.println(map);
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual, map);
			cidadeAtual = cadastroCidade.salvar(cidadeAtual);
			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
	

}
