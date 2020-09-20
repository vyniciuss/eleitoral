package com.vfs.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Zona implements java.io.Serializable {

	private static final long serialVersionUID = -5066891421698507213L;
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long numeroZona;
	@NotNull
	private Long limiteEleitores;
	@NotNull
	private String nomeJuiz;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Municipio municipio;
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "zona")
	private Set<ZonaMunicipio> zonaMunicipios = new HashSet<ZonaMunicipio>();

}