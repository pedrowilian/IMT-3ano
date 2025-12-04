# Servidor de Contagem de Conexões

Servidor que mantém um contador global com o número total de clientes conectados e informa cada cliente de seu número.

## Como executar

1. Compilar os arquivos:
```bash
javac ConnectionCounterServer.java
javac ConnectionCounterClient.java
```

2. Executar o servidor:
```bash
java ConnectionCounterServer
```

3. Em outros terminais, executar múltiplos clientes:
```bash
java ConnectionCounterClient
```

## Funcionalidades

- Variável compartilhada (static)
- Controle de concorrência com synchronized
- Thread por cliente
- Contador global de conexões
