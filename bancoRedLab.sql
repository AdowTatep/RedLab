DROP DATABASE IF EXISTS RedLab;
CREATE DATABASE RedLab;
USE RedLab;

CREATE TABLE config(
	id int primary key auto_increment,
	custoExamePadrao double not null    
);

CREATE TABLE usuario(
	login varchar(20) primary key,
    senha varchar(50) not null,
    isAdmin bool    
);

CREATE TABLE pessoa(	
    login varchar(20) primary key,
	nome varchar(150),
    telefone varchar(20),
    sexo char(1),
    endereco varchar(255),
    cpf char(11) unique,
    temPlanoSaude bool,
	CONSTRAINT fk_pessoa_usuario FOREIGN KEY(login) REFERENCES usuario(login)
);

CREATE TABLE exame(
	id int primary key auto_increment,
    custo double not null,
    descricao varchar(255),
    tempoJejum time,
    dataEntrega date,
    horaEntrega time,
    isEntregue bool,
    loginPessoa varchar(20),
	CONSTRAINT fk_exame_pessoa FOREIGN KEY(loginPessoa) REFERENCES pessoa(login)
);

INSERT INTO usuario VALUES(
	'admin', 'admin', true
);

INSERT INTO pessoa VALUES(
	'admin', 'Administrador', '', 'M', '', '99999999999', true
);