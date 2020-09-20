package com.vfs.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vfs.api.v1.model.input.CidadeInput;
import com.vfs.domain.model.Cidade;
import com.vfs.domain.model.Estado;

@Component
public class CidadeInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		cidade.setEstado(new Estado());
		modelMapper.map(cidadeInput, cidade);
	}
	
}
