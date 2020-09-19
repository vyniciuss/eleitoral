package com.vfs.domain.enumeration;

public enum SituacaoCadastro {

	INATIVO("Inativo"), 
	ATIVO("Ativo"), 
	INCOMPLETO_INCONSISTENTE("Incompleto/Inconsistente");

	private String descricao;

	SituacaoCadastro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
