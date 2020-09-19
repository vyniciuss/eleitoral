CREATE TABLE cidade (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    estado_id BIGINT NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB;

CREATE TABLE eleitor (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data_criacao DATETIME(6),
    data_nascimento DATETIME(6) NOT NULL,
    data_ultima_modificacao DATETIME(6),
    email VARCHAR(255) NOT NULL,
    endereco_bairro VARCHAR(255),
    endereco_cep VARCHAR(10),
    endereco_complemento VARCHAR(255),
    endereco_logradouro VARCHAR(255),
    endereco_numero VARCHAR(10),
    nome VARCHAR(255) NOT NULL,
    situacao_cadastro varchar(30) not null,
    titulo_eleitor VARCHAR(255) NOT NULL,
    endereco_cidade_id BIGINT,
    local_votacao_id BIGINT NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB;

CREATE TABLE estado (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB;

CREATE TABLE local_votacao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    num_local BIGINT NOT NULL,
    possui_rampa_na_calcada BIT,
    possui_vaga_deficiencia BIT,
    predio_reformado BIT,
    quantidade_urnas BIGINT NOT NULL,
    zona_municipio_id BIGINT NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB;

CREATE TABLE municipio (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome_municipio VARCHAR(255) NOT NULL,
    numero_municipio BIGINT NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB;

CREATE TABLE zona (
    id BIGINT NOT NULL AUTO_INCREMENT,
    limite_eleitores BIGINT NOT NULL,
    nome_juiz VARCHAR(255) NOT NULL,
    numero_zona BIGINT NOT NULL,
    municipio_id BIGINT NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB;

CREATE TABLE zona_municipio (
    id BIGINT NOT NULL AUTO_INCREMENT,
    municipio_id BIGINT NOT NULL,
    zona_id BIGINT NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB;

alter table cidade add constraint fk_cidade_estado foreign key (estado_id) references estado (id);
alter table eleitor add constraint fk_eleitor_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table eleitor add constraint fk_eleitor_local foreign key (local_votacao_id) references local_votacao (id);
alter table local_votacao add constraint fk_local_zona_municipio foreign key (zona_municipio_id) references zona_municipio (id);
alter table zona add constraint fk_zona_municipio foreign key (municipio_id) references municipio (id);
alter table zona_municipio add constraint fk_zm_municipio foreign key (municipio_id) references municipio (id);
alter table zona_municipio add constraint fk_zm_zona foreign key (zona_id) references zona (id);