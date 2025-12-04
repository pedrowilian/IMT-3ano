# Servidor de Agenda

Servidor que mantém uma lista de compromissos em memória e responde a comandos de adicionar, listar e remover compromissos.

## Como executar

1. Compilar os arquivos:
```bash
javac AgendaServer.java
javac AgendaClient.java
```

2. Executar o servidor:
```bash
java AgendaServer
```

3. Em outro terminal, executar o cliente:
```bash
java AgendaClient
```

## Comandos disponíveis

- `ADD <descrição> <horário>` - Adiciona um novo compromisso
- `LIST` - Lista todos os compromissos
- `DEL <número>` - Remove um compromisso pelo número
- `SAIR` - Encerra a conexão

## Exemplo de uso

```
> ADD Reunião 14:00
Compromisso adicionado: Reunião 14:00

> ADD Dentista 16:30
Compromisso adicionado: Dentista 16:30

> LIST
=== Compromissos ===
1. Reunião 14:00
2. Dentista 16:30
====================

> DEL 1
Compromisso removido: Reunião 14:00

> LIST
=== Compromissos ===
1. Dentista 16:30
====================

> SAIR
Conexão encerrada.
```

## Funcionalidades

- Gerenciamento de lista em memória (ArrayList)
- Parsing de comandos com split
- Validação de entrada
- CRUD básico (Create, Read, Delete)
- Cada cliente tem sua própria agenda (não compartilhada)
- Tratamento de erros para índices inválidos
