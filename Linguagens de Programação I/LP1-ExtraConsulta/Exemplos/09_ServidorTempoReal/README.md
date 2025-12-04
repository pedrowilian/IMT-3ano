# Servidor de Tempo Real (Clock Server)

Servidor que envia automaticamente a hora atual a cada 1 segundo para todos os clientes conectados, sem que eles precisem solicitar.

## Como executar

1. Compilar os arquivos:
```bash
javac ClockServer.java
javac ClockClient.java
```

2. Executar o servidor:
```bash
java ClockServer
```

3. Em outros terminais, executar múltiplos clientes:
```bash
java ClockClient
```

## Comportamento

O servidor possui uma thread dedicada que, a cada segundo:
1. Obtém a hora atual do sistema
2. Envia para todos os clientes conectados automaticamente
3. Não requer solicitação dos clientes

## Saída esperada (cliente)

```
Conectado ao Servidor de Tempo Real
(Pressione Ctrl+C para encerrar)

Conectado ao Servidor de Tempo Real
Você receberá a hora atual a cada segundo.
Hora atual: 14:23:45
Hora atual: 14:23:46
Hora atual: 14:23:47
Hora atual: 14:23:48
...
```

## Funcionalidades

- Thread de broadcast periódico no servidor
- Atualização automática sem solicitação do cliente
- Sincronização de todos os clientes conectados
- Formato de tempo HH:mm:ss
- Lista compartilhada de clientes para broadcast
