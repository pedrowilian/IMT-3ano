CREATE DATABASE cem_db;
USE cem_db;
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor_kwh DOUBLE NOT NULL,
    comodo1 VARCHAR(50),
    consumo1 DOUBLE,
    comodo2 VARCHAR(50),
    consumo2 DOUBLE,
    comodo3 VARCHAR(50),
    consumo3 DOUBLE
);

show databases;

use cem_db;

select * from clientes;