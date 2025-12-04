---

# **Exercícios propostos (parecidos com o exercício 5)**

## **1. Eco Server com Timestamp**

Implemente um servidor que receba mensagens dos clientes e devolva a mesma mensagem, porém prefixada com o horário atual (hh:mm:ss).
O cliente deve exibir tudo no console.

**Requisitos:**
– ServerSocket; Socket; BufferedReader/PrintWriter
– Thread por cliente
– Comunicação bidirecional

---

## **2. Servidor de Contagem de Conexões**

Implemente um servidor que mantém um contador global com o número total de clientes conectados.
Quando um cliente se conecta, o servidor envia imediatamente:
“Você é o cliente número X”.

Cada cliente deve simplesmente imprimir a mensagem recebida e encerrar.

**Requisitos:**
– Variável compartilhada (static)
– Controle de concorrência simples (synchronized)

---

## **3. Servidor de Reversão de Texto**

O cliente envia frases ao servidor.
O servidor retorna a frase invertida.

Exemplo:
Cliente envia: “Java Sockets”
Servidor responde: “stekcoS avaJ”

Encerrar quando o cliente digitar “FIM”.

---

## **4. Chat Broadcast Simples**

Implemente um servidor de chat que repassa para *todos os clientes conectados* a mensagem enviada por qualquer cliente.

**Exemplo:**
Cliente A: "Oi"
Clientes B, C recebem automaticamente: "A disse: Oi"

**Requisitos:**
– Lista compartilhada de PrintWriter de cada cliente
– Thread para cada cliente
– Repassar mensagens

---

## **5. Servidor de Operações Matemáticas**

O cliente envia comandos do tipo:
`SOMA 10 20`
`DIV 40 2`
`MULT 7 8`

O servidor interpreta, executa e devolve o resultado.

Se o comando for inválido, devolve: "ERRO".

Encerrar com `SAIR`.

---

# **Exercícios diferentes (mais criativos, mesmo tema)**

## **6. Servidor de Arquivos (versão simples)**

Cliente envia:
`GET nome.txt`
Servidor abre o arquivo local e envia seu conteúdo linha a linha para o cliente.

Se o arquivo não existir, devolve: “ARQUIVO NÃO ENCONTRADO”.

---

## **7. Cliente que envia mensagens automáticas**

Monte um cliente que a cada 2 segundos envia uma mensagem automática para o servidor (ex.: “ping”).
O servidor responde “pong”.
Encerrar ao enviar 10 mensagens.

Reforça: loop, sleep, I/O bloqueante.

---

## **8. Chat com identificação por nickname**

Ao conectar, o servidor pergunta:
“Digite seu nome:“

Depois disso, toda mensagem enviada pelo cliente deve chegar aos outros neste formato:
`[Felipe] Olá pessoal`

Treina lógica de identificação + broadcast.

---

## **9. Servidor de tempo real (Clock Server)**

O servidor envia automaticamente, a cada 1 segundo, a hora atual para todos os clientes conectados — sem que eles precisem solicitar.

Reforça: thread no servidor responsável apenas por broadcast periódico.

---

## **10. Servidor de Agenda**

Cliente manda comandos como:
`ADD Reunião 14:00`
`LIST`
`DEL 1`

O servidor mantém uma lista de compromissos **em memória** e responde conforme o comando.

Simples, direto, bom para prova.

---

## **11. Servidor de Mensagens Privadas**

Cada cliente informa um nome ao entrar.
O protocolo:
@joao Oi, tudo bem?
→ servidor entrega a mensagem apenas para o cliente "joao".
Mensagens sem @ viram broadcast.