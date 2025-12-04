# Servidor de Reversão de Texto

Servidor que recebe frases do cliente e retorna a frase invertida.

## Como executar

1. Compilar os arquivos:
```bash
javac TextReverseServer.java
javac TextReverseClient.java
```

2. Executar o servidor:
```bash
java TextReverseServer
```

3. Em outro terminal, executar o cliente:
```bash
java TextReverseClient
```

## Exemplo

```
Cliente envia: "Java Sockets"
Servidor responde: "stekcoS avaJ"
```

## Funcionalidades

- Inversão de texto usando StringBuilder
- Thread por cliente
- Comunicação bidirecional
- Encerramento com comando "FIM"
