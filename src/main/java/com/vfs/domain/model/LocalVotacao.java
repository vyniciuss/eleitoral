package com.vfs.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class LocalVotacao implements java.io.Serializable {

	private static final long serialVersionUID = 1270724155461374569L;
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(nullable = false)
	private ZonaMunicipio zonaMunicipio;
	@NotNull
	private Long numLocal;
	@NotNull
	private Long quantidadeUrnas;
	private Boolean predioReformado;
	private Boolean possuiRampaNaCalcada;
	private Boolean possuiVagaDeficiencia;

}