# Servidor de Arquivos

Servidor que permite aos clientes solicitar arquivos que são enviados linha a linha.

## Como executar

1. Criar o diretório de arquivos e adicionar arquivos de teste:
```bash
mkdir server_files
echo "Linha 1 de teste" > server_files/teste.txt
echo "Linha 2 de teste" >> server_files/teste.txt
```

2. Compilar os arquivos:
```bash
javac FileServer.java
javac FileClient.java
```

3. Executar o servidor:
```bash
java FileServer
```

4. Em outro terminal, executar o cliente:
```bash
java FileClient
```

## Comandos

- `GET <nome_arquivo>` - Solicita um arquivo do servidor
- `SAIR` - Encerra a conexão

## Exemplo de uso

```
> GET teste.txt

--- Conteúdo do arquivo ---
Linha 1 de teste
Linha 2 de teste
--- Fim do arquivo ---
```

## Funcionalidades

- Leitura de arquivos do diretório do servidor
- Envio linha a linha para o cliente
- Mensagem de erro se arquivo não existir
- Validação de comandos
