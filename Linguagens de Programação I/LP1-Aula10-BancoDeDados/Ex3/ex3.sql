create dabatase aula12;
use aula12;

create table Cliente(
    nome varchar(50) not null,
    cpf varchar(50) not null,
    primary key(cpf)
);

create table ContaCorrente(
    numero int not null,
    digito int not null,
    cpfCliente varchar(50) not null,
    primary key(numero, digito),
    foreign key(cpfCliente) references Cliente(cpf)
);

create table Agencia(
    numero int not null,
    digito int not null,
    primary key(numero, digito),
    foreign key(numero, digito) references ContaCorrente(numero, digito)
);
