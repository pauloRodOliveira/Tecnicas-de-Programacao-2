create database livraria;

CREATE TABLE livros(
	codigo INT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	preco SMALLMONEY NOT NULL
);

