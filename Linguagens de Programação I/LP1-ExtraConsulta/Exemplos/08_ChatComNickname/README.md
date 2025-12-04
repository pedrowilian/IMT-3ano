# Chat com Nickname

Servidor de chat onde cada cliente se identifica com um nickname e todas as mensagens são prefixadas com o nome do remetente.

## Como executar

1. Compilar os arquivos:
```bash
javac NicknameChatServer.java
javac NicknameChatClient.java
```

2. Executar o servidor:
```bash
java NicknameChatServer
```

3. Em vários terminais, executar múltiplos clientes:
```bash
java NicknameChatClient
```

## Fluxo de uso

1. Ao conectar, o servidor solicita: "Digite seu nome:"
2. Cliente informa seu nickname
3. Servidor notifica todos: "[nickname] entrou no chat"
4. Mensagens são formatadas como: "[nickname] mensagem"
5. Todos os outros clientes recebem as mensagens
6. Digite "SAIR" para sair do chat

## Exemplo

```
Cliente 1 (Felipe):
Digite seu nome:
> Felipe
[João] entrou no chat
> Olá pessoal
[Maria] Oi Felipe!

Cliente 2 (João):
Digite seu nome:
> João
[Felipe] Olá pessoal
> Tudo bem?

Cliente 3 (Maria):
Digite seu nome:
> Maria
[Felipe] Olá pessoal
[João] Tudo bem?
> Oi Felipe!
```

## Funcionalidades

- Identificação por nickname personalizado
- Broadcast com identificação do remetente
- Notificações de entrada/saída
- HashMap para gerenciar clientes
- Thread de escuta de mensagens no cliente
