# Eco Server com Timestamp

Servidor que recebe mensagens dos clientes e devolve a mesma mensagem prefixada com o horário atual (hh:mm:ss).

## Como executar

1. Compilar os arquivos:
```bash
javac EcoServer.java
javac EcoClient.java
```

2. Executar o servidor:
```bash
java EcoServer
```

3. Em outro terminal, executar o cliente:
```bash
java EcoClient
```

## Funcionalidades

- ServerSocket e Socket para comunicação
- Thread por cliente
- Comunicação bidirecional
- Timestamp em formato HH:mm:ss
