package com.vfs.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.vfs.domain.enumeration.SituacaoCadastro;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Eleitor implements Serializable {

	private static final long serialVersionUID = -1225899483084121368L;
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@NotNull
	private String nome;
	@NotNull
	private Date dataNascimento;
	@NotBlank
	@NotNull
	private String tituloEleitor;
	@Embedded
	private Endereco endereco;
	@ManyToOne
	@JoinColumn(nullable = false)
	private LocalVotacao localVotacao;
	@Email
	@NotBlank
	@NotNull
	private String email;
	@NotNull
	@Enumerated(EnumType.STRING)
	private SituacaoCadastro situacaoCadastro;
	@CreationTimestamp
	@Column(updatable = false)
	private OffsetDateTime dataCriacao;
	@LastModifiedDate
	private OffsetDateTime dataUltimaModificacao;

}
