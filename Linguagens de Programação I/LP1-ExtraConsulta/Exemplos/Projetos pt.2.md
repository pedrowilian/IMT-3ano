# **+10 exercícios com múltiplos clientes**

---

## **1. Leilão em Tempo Real**

O servidor mantém:

* item atual
* maior lance
* nome do cliente que deu o lance

Clientes enviam:
`LANCE 150`

Servidor:

* Atualiza se o lance for maior
* Envia **para todos**:
  `NOVO_LANCE 150 por Felipe`

Quando o servidor recebe `ENCERRAR`, envia:
`LEILAO_FINALIZADO vencedor=Felipe`

---

## **2. Sala de Chat com Canais**

Clientes podem entrar em canais diferentes.

Protocolo:

* `/join sala1`
* `/join sala2`

Mensagens são broadcast **apenas para quem está na mesma sala**.

---

## **3. Jogo da Forca Multiplayer**

Servidor tem:

* palavra secreta
* estado atual
* letras já usadas

Clientes enviam:
`LETRA A`
Servidor:

* Atualiza estado
* Envia para todos:
  `ATUAL:_A_A_`
  `ERRADAS: F, T`

Quando alguém completa, servidor anuncia vencedor para todos.

---

## **4. Votação em grupo**

Servidor envia uma pergunta e opções.

Clientes mandam:
`VOTO A`
`VOTO B`

Servidor mantém contagem global.

Quando recebe `/resultado`, envia a todos:
`A=3, B=5, C=1`.

---

## **5. Servidor de Filas**

Clientes podem:

* inserir na fila global: `PUSH texto`
* remover: `POP`

Quando alguém faz `POP`, todos recebem broadcast indicando quem removeu e qual item saiu.

---

## **6. Multicast de Logs de Sistema**

Cada cliente representa um “agente” enviando logs.

Servidor recebe:
`LOG agente42 CPU=90%`
e repassa **para todos**.

Treina broadcast + identificação de cliente.

---

## **7. Ranking Global de Pontos**

Cada cliente possui pontos iniciando em 0.

Comandos:
`ADD 10`
`SUB 5`
`GET` → retorna apenas para o cliente
`RANK` → envia a lista para todos

O servidor deve manter ranking ordenado.

---

## **8. Relógio Sincronizado com Ping**

Todo cliente envia a cada X segundos:
`PING nome timestamp`

Servidor:

* calcula latência estimada (timestamp diferença)
* envia broadcast:
  `latencia(Felipe)=23ms`

Treina manipulação de múltiplos dados simultâneos.

---

## **9. Chat com Silenciamento**

Clientes podem silenciar outros:

`/mute joao`

O servidor continua broadcast normal,
**mas o cliente deve ignorar mensagens vindas dos usuários silenciados**.

Exige lógica por cliente, não pelo servidor.

---

## **10. Broadcast de Estados de Sensores**

Cada cliente representa um sensor.

Envia a cada 5s:
`TEMP 23.1`
`UMID 45%`

Servidor agrega e envia broadcast:

```
--- ESTADO GERAL ---
Sensor1: 23.1 °C / 45%
Sensor2: 21.9 °C / 40%
Sensor3: offline
```

Exige:

* tabela global de estados
* timeout por sensor
* broadcast periódico

---