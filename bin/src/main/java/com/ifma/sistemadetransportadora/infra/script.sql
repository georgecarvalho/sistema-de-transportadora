CREATE DATABASE transportadora;

use transportadora;

CREATE TABLE IF NOT EXISTS Cliente (
  codigo_cliente INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(30),
  endereco VARCHAR(30),
  telefone VARCHAR(30)
);

INSERT INTO Cliente(nome, endereco, telefone) 
VALUES ('George', 'Alameda Paço do Lumiar, 36', '(98)98332-2346'),
		('Cássia','Alameda Paço do Lumiar, 36', '(98)98888-8888'),
        ('Háida','Alameda Paço do Lumiar, 36', '(98)97777-7777');
        
TRUNCATE TABLE Cliente;

SELECT * FROM Cliente;

SELECT nome, endereco, telefone FROM Cliente 
WHERE codigo_cliente = 1;

CREATE TABLE IF NOT EXISTS Cidade (
  codigo_cidade INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(30),
  UF VARCHAR(30),
  taxa FLOAT
  );

INSERT INTO Cidade(nome, UF, taxa) 
VALUES ('São Luís', 'MA', '0.2'),
		('São José de Ribamar', 'MA','0.4'),
        ('Paço do Lumiar','MA','0.6');

TRUNCATE TABLE Cidade;

SELECT * FROM Cidade;

SELECT nome, UF, taxa FROM Cidade
WHERE codigo_cidade = 1;

SELECT nome, UF, taxa FROM Cidade
WHERE nome = 'São Luís';

SELECT nome, UF, taxa
FROM Cidade;

UPDATE Cidade SET nome='Belém', UF='PA', taxa=0.5
WHERE codigo_cidade = 1;

CREATE TABLE IF NOT EXISTS Frete (
  codigo_frete INT PRIMARY KEY AUTO_INCREMENT,
  codigo_cidade INT NOT NULL,
  codigo_cliente INT NOT NULL,
  descricao VARCHAR(30),
  peso FLOAT,
  valor FLOAT,
  CONSTRAINT fk_Frete_Cliente
    FOREIGN KEY (codigo_cliente)
    REFERENCES Cliente (codigo_cliente),
  CONSTRAINT fk_Frete_Cidade1
    FOREIGN KEY (codigo_cidade)
    REFERENCES Cidade (codigo_cidade)
);

INSERT INTO Frete (codigo_cidade, codigo_cliente, descricao, peso, valor)
VALUES (1, 1, 'Mudança', 100, 70),
		(1, 2, 'Carne', 20, 55),
        (2, 3, 'Banana', 10, 50);

TRUNCATE TABLE Frete;

SELECT * FROM Frete;

SELECT cd.nome as destino, ft.valor FROM Cidade cd
INNER JOIN Frete ft on cd.codigo_cidade = ft.codigo_cidade
WHERE ft.valor = round(ft.peso*10 + cd.taxa, 2)
GROUP BY cd.codigo_cidade;

SELECT ft.codigo_frete, cd.codigo_cidade, cl.codigo_cliente, ft.descricao, ft.peso, ft.valor
FROM Cliente cl
INNER JOIN Frete ft ON cl.codigo_cliente = ft.codigo_cliente
INNER JOIN Cidade cd ON ft.codigo_cidade = cd.codigo_cidade
WHERE cl.codigo_cliente = 2;

SELECT MAX(valor) AS valor, codigo_frete, descricao, valor FROM Frete;

SELECT cd.nome, MAX(ft.codigo_cidade) AS QuantidadeFretes FROM Frete ft
INNER JOIN Cidade cd ON cd.codigo_cidade = ft.codigo_cidade;
