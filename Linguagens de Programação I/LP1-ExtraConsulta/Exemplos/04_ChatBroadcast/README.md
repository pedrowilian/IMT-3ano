# Chat Broadcast Simples

Servidor de chat que repassa para todos os clientes conectados as mensagens enviadas por qualquer cliente.

## Como executar

1. Compilar os arquivos:
```bash
javac ChatServer.java
javac ChatClient.java
```

2. Executar o servidor:
```bash
java ChatServer
```

3. Em vários terminais, executar múltiplos clientes:
```bash
java ChatClient
```

## Exemplo de uso

```
Cliente A: "Olá"
Clientes B, C recebem: "Cliente_A disse: Olá"
```

## Funcionalidades

- Lista compartilhada de PrintWriter de cada cliente
- Thread para cada cliente
- Broadcast de mensagens para todos os clientes
- Notificação quando clientes entram/saem
- Comando SAIR para encerrar
