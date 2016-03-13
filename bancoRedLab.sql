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
    sexo varchar(1),
    cpf varchar(11) unique,    
    endereco varchar(255),
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
	'admin', 'Administrador', '', 'M', '99999999999', 'Rua administrador', true
);

INSERT INTO usuario VALUES(
	'login', 'senha', false
);

INSERT INTO pessoa VALUES(
	'login', 'Usuário', '12345678', 'M', '11111111111', 'Rua usuário', false
);

INSERT INTO exame VALUES
	(1, 50.00, 'Radiografia', null, '2016-02-18', '14:24:44', 1, 'login'),
    (2, 10.00, 'Exame de sangue', '07:00:00', '2016-02-27', '16:10:34', 0, 'login');