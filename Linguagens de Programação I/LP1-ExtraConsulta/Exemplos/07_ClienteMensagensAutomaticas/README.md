# Cliente de Mensagens Automáticas (Ping-Pong)

Cliente que envia automaticamente mensagens "ping" para o servidor a cada 2 segundos, recebendo "pong" como resposta.

## Como executar

1. Compilar os arquivos:
```bash
javac PingPongServer.java
javac AutoPingClient.java
```

2. Executar o servidor:
```bash
java PingPongServer
```

3. Em outro terminal, executar o cliente:
```bash
java AutoPingClient
```

## Comportamento

O cliente envia automaticamente 10 mensagens "ping" com intervalo de 2 segundos entre cada uma. O servidor responde com "pong" a cada ping recebido.

## Saída esperada (cliente)

```
[1/10] Enviado: ping
[1/10] Recebido: pong

[2/10] Enviado: ping
[2/10] Recebido: pong

...

10 mensagens enviadas. Encerrando conexão.
```

## Funcionalidades

- Loop automático com Thread.sleep()
- Envio periódico de mensagens
- Contador de mensagens
- I/O bloqueante
- Encerramento automático após 10 mensagens
