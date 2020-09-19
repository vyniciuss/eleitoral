set foreign_key_checks = 0;

delete from cidade;
delete from estado;
delete from eleitor;
delete from local_votacao;
delete from municipio;
delete from zona;
delete from zona_municipio;


set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table estado auto_increment = 1;
alter table eleitor auto_increment = 1;
alter table local_votacao auto_increment = 1;
alter table municipio auto_increment = 1;
alter table zona auto_increment = 1;
alter table zona_municipio auto_increment = 1;


insert into municipio (id, nome_municipio, numero_municipio) values (1, 'Aracaju' , 22);
insert into municipio (id, nome_municipio, numero_municipio) values (2, 'São Paulo', 33);
insert into municipio (id, nome_municipio, numero_municipio) values (3, 'Rio de Janeiro', 44);
insert into municipio (id, nome_municipio, numero_municipio) values (4, 'Fortaleza', 55);

insert into estado (id, nome) values (1, 'Sergipe');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into zona (id, numero_zona, limite_eleitores, nome_juiz, municipio_id) values (1, 150, 100, 'Joao Leonardo', 1);
insert into zona (id, numero_zona, limite_eleitores, nome_juiz, municipio_id) values (2, 55, 99, 'Carlos Rocha', 2);
insert into zona (id, numero_zona, limite_eleitores, nome_juiz, municipio_id) values (3, 63, 5000, 'Maria Cristina',4);
insert into zona (id, numero_zona, limite_eleitores, nome_juiz, municipio_id) values (4, 99, 3000, 'Cleide Aguia', 3);
insert into zona (id, numero_zona, limite_eleitores, nome_juiz, municipio_id) values (5, 300, 150, 'Joao Ribeiro', 1);

insert into zona_municipio (id, municipio_id, zona_id) values (1, 1, 1);
insert into zona_municipio (id, municipio_id, zona_id) values (2, 1, 2);
insert into zona_municipio (id, municipio_id, zona_id) values (3, 2, 1);


insert into local_votacao (id, num_local, possui_rampa_na_calcada, possui_vaga_deficiencia, predio_reformado, quantidade_urnas, zona_municipio_id) 
values (1, 123, true, true, true, 10, 1);
insert into local_votacao (id, num_local, possui_rampa_na_calcada, possui_vaga_deficiencia, predio_reformado, quantidade_urnas, zona_municipio_id) 
values (2, 44, true, true, true, 60, 2);
insert into local_votacao (id, num_local, possui_rampa_na_calcada, possui_vaga_deficiencia, predio_reformado, quantidade_urnas, zona_municipio_id) 
values (3, 66, true, true, true, 20, 3);


insert into eleitor (id, data_criacao, data_nascimento, data_ultima_modificacao, email, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, situacao_cadastro, titulo_eleitor, endereco_cidade_id, local_votacao_id) 
values (1, utc_timestamp, utc_timestamp, utc_timestamp, 'joao@gmail.com', 'Jurupina', '49000-000', 'Casa A', 'Rua Veridiana', 61, 'João Da Silva', 'ATIVO', '11111111111', 1, 1);

insert into eleitor (id, data_criacao, data_nascimento, data_ultima_modificacao, email, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, situacao_cadastro, titulo_eleitor, endereco_cidade_id, local_votacao_id) 
values (2, utc_timestamp, utc_timestamp, utc_timestamp, 'maria@gmail.com', 'Cacai', '49000-000', 'Casa B', 'Rua Mariana', 22, 'Carlos Ribeiro', 'INATIVO', '2222222222', 2, 2);



