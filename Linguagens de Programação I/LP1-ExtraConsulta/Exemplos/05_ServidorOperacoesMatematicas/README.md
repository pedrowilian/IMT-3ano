# Servidor de Operações Matemáticas

Servidor que interpreta comandos matemáticos, executa e retorna o resultado.

## Como executar

1. Compilar os arquivos:
```bash
javac MathServer.java
javac MathClient.java
```

2. Executar o servidor:
```bash
java MathServer
```

3. Em outro terminal, executar o cliente:
```bash
java MathClient
```

## Comandos suportados

- `SOMA <num1> <num2>` - Soma dois números
- `SUB <num1> <num2>` - Subtrai dois números
- `MULT <num1> <num2>` - Multiplica dois números
- `DIV <num1> <num2>` - Divide dois números
- `SAIR` - Encerra a conexão

## Exemplos

```
> SOMA 10 20
Resultado: 30.0

> DIV 40 2
Resultado: 20.0

> MULT 7 8
Resultado: 56.0
```

## Funcionalidades

- Parsing de comandos
- Operações matemáticas básicas
- Validação de entrada
- Tratamento de erros (divisão por zero, comandos inválidos)
