package com.vfs.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Municipio implements java.io.Serializable {

	private static final long serialVersionUID = 612854214824798593L;
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nomeMunicipio;
	@NotNull
	private Long numeroMunicipio;
	@Builder.Default
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "municipio")
	private Set<ZonaMunicipio> zonaMunicipios = new HashSet<ZonaMunicipio>();

}