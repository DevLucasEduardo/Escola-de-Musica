CREATE DATABASE IF NOT EXISTS escola_de_musica;
USE escola_de_musica;
 drop database escola_de_musica;

create table aula (
    nome_aula varchar(20),
    aula_id int not null auto_increment,
    primary key (aula_id)
);

CREATE TABLE IF NOT EXISTS professor (
	id int NOT NULL AUTO_INCREMENT,
	nome varchar(45) NOT NULL,
	sobrenome varchar(45) NOT NULL,
	cpf varchar(45) NOT NULL,
	email varchar(45) NOT NULL,
	dataNasc date NOT NULL,
	genero varchar(45) NOT NULL,
	estado varchar(45) NOT NULL default 'SP',
	cidade varchar(45) NOT NULL,
	bairro varchar(45) NOT NULL,
	rua varchar(45) NOT NULL,
	numero int DEFAULT NULL,
    aula_prof_fk int not null,
	PRIMARY KEY (id),
    foreign key (aula_prof_fk) references aula(aula_id)
);

CREATE TABLE IF NOT EXISTS aluno (
    nome varchar(40) not null,
    sobrenome varchar(100) not null,
    cpf varchar(14) not null,
    email varchar(100) not null,
    senha varchar(30) not null,
    data_nasc date not null,
    genero varchar(45) not null,
    estado varchar(2) not null default 'SP',
    cidade varchar(25) not null,
    bairro varchar(25) not null,
    rua varchar(25) not null,
    numero int not null,
    aluno_id int not null auto_increment,
    aula_aluno_fk int not null,
    primary key (aluno_id),
    foreign key (aula_aluno_fk) references aula(aula_id)
);

insert into aula values('Baixo', default);
insert into aula values('Bateria', default);
insert into aula values('Canto', default);
insert into aula values('Guitarra', default);
insert into aula values('Piano', default);
insert into aula values('Violão', default);

-- AF
CREATE TABLE fornecedor (
    cnpj 				VARCHAR(18) NOT NULL UNIQUE,
    fornecedor 			VARCHAR(30) NOT NULL,
    razao_social 		VARCHAR(50) NOT NULL,
	pais 				varchar(30) NOT NULL DEFAULT 'brasil',
    estado 				VARCHAR(30) NOT NULL,
    cidade 				VARCHAR(30) NOT NULL,
    codigo_postal 		VARCHAR(30) NOT NULL,
    PRIMARY KEY (cnpj)
);

CREATE TABLE instrumento (
    instrumento 		VARCHAR(30) NOT NULL,
    categoria 			VARCHAR(15) NOT NULL,
    marca 				VARCHAR(20) NOT NULL,
    codigo_instrumento 	INT NOT NULL AUTO_INCREMENT,
    fk_fornecedor 		varchar(18) NOT NULL,
    
    PRIMARY KEY (codigo_instrumento),
    FOREIGN KEY (fk_fornecedor) 
		REFERENCES fornecedor (cnpj)
);


insert into fornecedor values("11.111.1-01", "solo", "solo instrumentos", "brasil", "SP", "Sorocaba", "18123");
insert into fornecedor values("11.111.1-31", "phenix", "phenix instrumentos", "brasil", "SP", "Campinas", "13893");
insert into instrumento values("violao", "cordas", "crafter", default, "11.111.1-31");
insert into instrumento values("piano", "teclas", "yamaha", default, "11.111.1-01");