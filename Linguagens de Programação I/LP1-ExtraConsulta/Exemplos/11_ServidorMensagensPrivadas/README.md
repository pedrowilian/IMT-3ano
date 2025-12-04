# 11. Servidor de Mensagens Privadas

Sistema de chat com suporte a mensagens privadas e broadcast.

## 📋 Descrição

Cliente manda comandos:
- `@usuario mensagem` → Mensagem privada apenas para o usuário especificado
- `mensagem normal` → Broadcast para todos os usuários conectados
- `/users` ou `/list` → Lista todos os usuários online
- `/quit` ou `/exit` → Sair do chat

## ✅ CORREÇÕES IMPLEMENTADAS

### Problema Identificado:
- Nicknames não eram enviados corretamente ao servidor
- Cliente GUI conectava mas não registrava o usuário
- Mensagens não eram transmitidas entre clientes

### Solução:
- ✅ Cliente GUI agora envia o nickname automaticamente quando o servidor solicita
- ✅ Protocolo de comunicação corrigido (nickname enviado na hora certa)
- ✅ Validação de nickname duplicado funciona corretamente
- ✅ Broadcast de mensagens funcionando
- ✅ Mensagens privadas funcionando
- ✅ Lista de usuários atualizada em tempo real

## 🎯 Características

- **Mensagens Privadas**: Use `@nickname mensagem` para enviar mensagens privadas
- **Broadcast**: Mensagens sem `@` são enviadas para todos
- **Lista de Usuários**: Veja quem está online em tempo real
- **Interface Gráfica**: Cliente GUI completo com lista de usuários
- **Nicknames Únicos**: Sistema impede nicknames duplicados
- **Confirmação**: Remetente vê confirmação de mensagens privadas enviadas

## 🚀 Como Executar

### Opção 1: Tudo Automático (RECOMENDADO)
```batch
compile.bat
run_all.bat
```

### Opção 2: Manual
```batch
# Compilar
compile.bat

# Servidor
run_server.bat

# Clientes (abrir múltiplas instâncias)
run_client_gui.bat
```

### Opção 3: Cliente Console
```batch
run_client.bat
```

## 💻 Comandos Disponíveis

| Comando | Descrição |
|---------|-----------|
| `@usuario mensagem` | Envia mensagem privada |
| `mensagem` | Envia para todos (broadcast) |
| `/users` ou `/list` | Lista usuários online |
| `/quit` ou `/exit` | Sair do chat |

## 🎨 Interface GUI

### Recursos da Interface:
- ✅ **Área de Chat** - Visualização de todas as mensagens
- ✅ **Lista de Usuários** - Veja quem está online
- ✅ **Botão @Privado** - Envie mensagens privadas facilmente
- ✅ **Duplo Clique** - Clique 2x em um usuário para mensagem privada
- ✅ **Atualizar Lista** - Botão para atualizar usuários online
- ✅ **Cores Diferenciadas** - Mensagens privadas aparecem destacadas

## 🔧 Tecnologias

- **Java Socket Programming**
- **Multi-threading** (Thread por cliente)
- **ConcurrentHashMap** (Gerenciamento thread-safe de clientes)
- **Java Swing** (Interface gráfica)
- **PrintWriter/BufferedReader** (Comunicação)

## 📡 Porta

- **5010** - Servidor de Mensagens Privadas

## 📝 Exemplo de Uso

### Servidor:
```
======================================
  SERVIDOR DE MENSAGENS PRIVADAS
  Porta: 5010
======================================

Servidor iniciado! Aguardando conexões...

[+] Alice entrou no chat (127.0.0.1)
[+] Bob entrou no chat (127.0.0.1)
[Alice]: Olá a todos!
  -> Mensagem privada de Alice para Bob
[Bob]: Olá Alice!
```

### Cliente:
```
SERVER: Digite seu nickname:
> Alice
SERVER: Bem-vindo, Alice!
SERVER: Use @username mensagem para mensagem privada
SERVER: Mensagens sem @ são enviadas para todos

> Olá a todos!
[Alice]: Olá a todos!

> @Bob Oi Bob, tudo bem?
[PRIVADO para Bob]: Oi Bob, tudo bem?

[PRIVADO de Bob]: Tudo ótimo! E você?
```

## 🎓 Conceitos Treinados

- Gerenciamento de múltiplos clientes conectados
- Identificação de usuários por nickname
- Roteamento de mensagens (privadas vs broadcast)
- Validação de comandos
- Interface gráfica interativa
- Thread-safety com coleções concorrentes
- Tratamento de desconexões

## ⚠️ Observações

- Nicknames devem ser únicos
- Mensagens privadas são case-sensitive para o nickname
- A lista de usuários é atualizada automaticamente quando alguém entra/sai
- O servidor registra todas as atividades no console
