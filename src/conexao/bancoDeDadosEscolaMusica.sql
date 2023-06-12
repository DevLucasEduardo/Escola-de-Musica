drop database if exists escola_de_musica;
CREATE DATABASE IF NOT EXISTS escola_de_musica;
USE escola_de_musica;

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
	pais 				varchar(30) NOT NULL,
    estado 				VARCHAR(30) NOT NULL,
    cidade 				VARCHAR(30) NOT NULL,
    codigo_postal 		VARCHAR(30) NOT NULL,
    PRIMARY KEY (cnpj)
);

CREATE TABLE instrumento (
    instrumento 		VARCHAR(30) NOT NULL,
    categoria 			VARCHAR(15) NOT NULL,
    marca 				VARCHAR(20) NOT NULL,
    codigo_instrumento 	INT NOT NULL,
    fk_fornecedor 		varchar(18) NOT NULL,
    
    PRIMARY KEY (codigo_instrumento),
    FOREIGN KEY (fk_fornecedor) 
		REFERENCES fornecedor (cnpj)
);

CREATE TABLE compra(
	quantidade int not null,
	id_compra int not null,
	fk_funcionario int not null,
    fk_instrumento int not null,
    primary key (id_compra),
    foreign key (fk_instrumento) references instrumento (codigo_instrumento),
    foreign key (fk_funcionario) references professor (id)
);

INSERT INTO professor values(default, 'Lucas', 'Pereira', '123.123.123-12', 'lucas@email', '1998-01-01', 'M', 'SP', 'Sorocaba', 'Jd Florida', 'Alameda Flores', 100, 1);
INSERT INTO professor VALUES (default, 'John', 'Smith', '111.222.333-44', 'john.smith@email', '1985-07-15', 'M', 'SP', 'São Paulo', 'Centro', 'Rua das Flores', 50, 2);
INSERT INTO professor VALUES (default, 'Maria', 'Rodriguez', '555.666.777-88', 'maria.rodriguez@email', '1990-03-25', 'F', 'RJ', 'Rio de Janeiro', 'Copacabana', 'Avenida Atlântica', 500, 3);
INSERT INTO professor VALUES (default, 'Robert', 'Johnson', '999.888.777-66', 'robert.johnson@email', '1980-11-03', 'M', 'RS', 'Porto Alegre', 'Centro', 'Rua dos Artistas', 25, 1);
INSERT INTO professor VALUES (default, 'Emily', 'Johnson', '444.555.666-77', 'emily.johnson@email', '1992-09-12', 'F', 'MG', 'Belo Horizonte', 'Savassi', 'Avenida Contorno', 150, 2);
INSERT INTO professor VALUES (default, 'Ricardo', 'Santos', '777.888.999-00', 'ricardo.santos@email', '1987-04-18', 'M', 'SC', 'Florianópolis', 'Centro', 'Rua das Palmeiras', 75, 3);


select * from professor;
select * from instrumento;
select * from fornecedor;
select * from compra;