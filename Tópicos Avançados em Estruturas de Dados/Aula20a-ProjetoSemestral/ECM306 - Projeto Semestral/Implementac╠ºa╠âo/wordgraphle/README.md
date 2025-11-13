# WordGraphle - Documentação do Projeto

## 📋 Índice
- [Visão Geral](#visão-geral)
- [Arquitetura do Sistema](#arquitetura-do-sistema)
- [Estrutura de Pacotes](#estrutura-de-pacotes)
- [Fluxo de Dados](#fluxo-de-dados)
- [Componentes Principais](#componentes-principais)
- [Algoritmos e Lógica](#algoritmos-e-lógica)
- [Interface Gráfica](#interface-gráfica)

---

## 🎯 Visão Geral

**WordGraphle** é um jogo inspirado no Wordle, desenvolvido em Java, que adiciona funcionalidades avançadas como:
- Suporte a palavras em português (com acentuação)
- Sistema de grafo para análise de palavras
- Sugestões inteligentes baseadas em frequências
- Visualização gráfica das relações entre letras

### Características Principais
- **L = 5**: Palavras de 5 letras
- **MAX_TRIES = 6**: Até 6 tentativas
- **Dicionário PT-BR**: Com preservação de acentos para exibição
- **Normalização**: Lógica do jogo usa A–Z sem acentos

---

## 🏗️ Arquitetura do Sistema

```mermaid
graph TB
    subgraph "Camada de Aplicação"
        A[WordGraphleApp]
    end
    
    subgraph "Camada de Interface"
        B[WordGraphleFrame]
        C[GraphPanel]
        D[KeyboardPanel]
    end
    
    subgraph "Camada de Lógica"
        E[GameEngine]
        F[Solver]
    end
    
    subgraph "Camada de Modelo"
        G[Dictionary]
        H[GraphModel]
        I[Constraints]
        J[Feedback]
        K[FeedbackColor]
    end
    
    A --> B
    B --> E
    B --> F
    B --> C
    B --> D
    E --> G
    E --> I
    E --> J
    F --> H
    F --> I
    H --> G
    C --> H
    
    style A fill:#e1f5ff
    style E fill:#ffe1e1
    style G fill:#e1ffe1
    style B fill:#fff4e1
```

---

## 📦 Estrutura de Pacotes

```
wordgraphle/
├── 📄 WordGraphleApp.java          # Ponto de entrada da aplicação
│
├── 🎮 engine/
│   └── GameEngine.java              # Motor do jogo (lógica principal)
│
├── 📊 graph/
│   └── GraphModel.java              # Modelo de grafo em camadas (DAG)
│
├── 📚 model/
│   ├── Dictionary.java              # Carregamento e normalização de palavras
│   ├── Constraints.java             # Sistema de restrições (verde/amarelo/cinza)
│   ├── Feedback.java                # Resultado de um palpite
│   └── FeedbackColor.java           # Enum: GREEN, YELLOW, GRAY
│
├── 🧠 solver/
│   └── Solver.java                  # Algoritmo de sugestões inteligentes
│
└── 🎨 ui/
    ├── WordGraphleFrame.java        # Janela principal (grade + abas)
    ├── GraphPanel.java              # Visualização do grafo
    └── KeyboardPanel.java           # Teclado visual com estados
```

---

## 🔄 Fluxo de Dados

### 1. Inicialização do Jogo

```mermaid
sequenceDiagram
    participant App as WordGraphleApp
    participant Frame as WordGraphleFrame
    participant Engine as GameEngine
    participant Dict as Dictionary
    participant Graph as GraphModel
    
    App->>Frame: Criar(L=5, maxTries=6)
    Frame->>Dict: loadFromResource("palavras.txt")
    Dict-->>Frame: Dictionary carregado
    Frame->>Engine: new GameEngine(dict)
    Engine->>Engine: pickNewSecret()
    Frame->>Graph: fromDictionary(dict)
    Graph->>Graph: Construir pesos de transição
    Frame->>Frame: Montar interface (grade, tabs)
```

### 2. Fluxo de um Palpite

```mermaid
sequenceDiagram
    participant User as Usuário
    participant Frame as WordGraphleFrame
    participant Engine as GameEngine
    participant Solver as Solver
    participant Graph as GraphModel
    
    User->>Frame: Digita "CARRO"
    Frame->>Engine: evaluate("CARRO")
    Engine->>Engine: Normalizar → "CARRO"
    Engine->>Engine: Comparar com palavra secreta
    Engine-->>Frame: Feedback (cores)
    Frame->>Frame: Atualizar grade visual
    Frame->>Engine: Atualizar constraints
    Engine->>Graph: applyConstraints()
    Frame->>Solver: filterCandidates()
    Solver-->>Frame: Lista de candidatos
    Frame->>Solver: suggestTop()
    Solver-->>Frame: Top 30 sugestões
    Frame->>Frame: Atualizar UI (teclado, sugestões)
```

---

## 🔧 Componentes Principais

### 1. GameEngine (Motor do Jogo)

```mermaid
classDiagram
    class GameEngine {
        -Dictionary dict
        -String secret
        -String secretDisplay
        -Constraints constraints
        -Random rng
        +GameEngine(dict)
        +pickNewSecret()
        +evaluate(rawGuess) Feedback
        +secret() String
        +secretDisplay() String
        +constraints() Constraints
    }
    
    class Dictionary {
        -int L
        -List~String~ words
        -Map~String,String~ display
        +loadFromResource(path, L) Dictionary
        +normalize(word) String
        +displayOf(canonical) String
    }
    
    class Constraints {
        -int L
        +int[] fixed
        +boolean[][] bannedPos
        +int[] minCount
        +int[] maxCount
        +idx(char) int
    }
    
    class Feedback {
        +String guess
        +FeedbackColor[] colors
    }
    
    GameEngine --> Dictionary
    GameEngine --> Constraints
    GameEngine --> Feedback
```

**Responsabilidades:**
- Sortear palavra secreta
- Avaliar palpites e gerar feedback colorido
- Atualizar restrições a cada rodada
- Normalizar entrada do usuário

---

### 2. GraphModel (Modelo de Grafo)

```mermaid
graph LR
    subgraph "Camada 0 (Posição 0)"
        A0[A]
        B0[B]
        C0[C]
    end
    
    subgraph "Camada 1 (Posição 1)"
        A1[A]
        B1[B]
        C1[C]
    end
    
    subgraph "Camada 2 (Posição 2)"
        A2[A]
        B2[B]
        C2[C]
    end
    
    A0 -->|peso=12| A1
    A0 -->|peso=5| B1
    B0 -->|peso=8| A1
    C0 -->|peso=15| A1
    A1 -->|peso=20| A2
    B1 -->|peso=7| C2
    
    style A0 fill:#90EE90
    style A1 fill:#FFD700
    style C2 fill:#D3D3D3
```

**Estrutura:**
- **Grafo Dirigido Acíclico (DAG)** em camadas
- **L camadas**: Uma para cada posição da palavra
- **26 nós por camada**: Uma para cada letra (A-Z)
- **Arestas com pesos**: Número de palavras com essa transição

**Exemplo:**
- Aresta (pos=0, 'C') → (pos=1, 'A') com peso=150
- Significa: 150 palavras no dicionário começam com "CA"

---

### 3. Sistema de Restrições (Constraints)

```mermaid
graph TD
    A[Palpite: CARRO] --> B{Comparar com Secreta}
    B --> C[🟩 GREEN: Posição correta]
    B --> D[🟨 YELLOW: Letra existe, posição errada]
    B --> E[⬜ GRAY: Letra não existe]
    
    C --> F[fixed[pos] = letra]
    D --> G[bannedPos[pos][letra] = true]
    D --> H[minCount[letra]++]
    E --> I[maxCount[letra] = contagem atual]
    
    F --> J[Constraints atualizados]
    G --> J
    H --> J
    I --> J
    
    J --> K[Filtrar candidatos]
    K --> L[Palavras válidas restantes]
    
    style C fill:#90EE90
    style D fill:#FFD700
    style E fill:#D3D3D3
```

**Tipos de Restrições:**

1. **Posição Fixa** (`fixed[]`)
   - Se letra está verde na posição i → `fixed[i] = letra`

2. **Proibição Local** (`bannedPos[][]`)
   - Se letra está amarela/cinza na posição i → `bannedPos[i][letra] = true`

3. **Contagem Mínima** (`minCount[]`)
   - Letra amarela/verde → aumenta `minCount[letra]`

4. **Contagem Máxima** (`maxCount[]`)
   - Letra cinza → define `maxCount[letra]` como contagem atual

---

### 4. Solver (Sistema de Sugestões)

```mermaid
flowchart TD
    A[Lista de Candidatos] --> B[Calcular Frequências]
    B --> C{Usar Grafo?}
    
    C -->|Sim| D[graph.positionLetterScores]
    C -->|Não| E[positionFrequencies em candidatos]
    
    D --> F[Matriz de frequências L x 26]
    E --> F
    
    F --> G[Para cada candidato]
    G --> H[Calcular score]
    H --> I[score = Σ freq + bônus diversidade]
    I --> J[Ordenar por score DESC]
    J --> K[Retornar Top K]
    
    style D fill:#FFE4B5
    style E fill:#E0FFFF
    style I fill:#FFB6C1
```

**Algoritmo de Pontuação:**
```
score(palavra) = Σ(freq[pos][letra]) + 0.02 × (letras únicas)
```

**Estratégias:**
1. **Baseada em candidatos**: Conta frequências apenas nas palavras ainda possíveis
2. **Baseada em grafo**: Usa estrutura do grafo para estimar frequências globais

---

## 🎨 Interface Gráfica

### Estrutura da Janela Principal

```
┌─────────────────────────────────────────────────────────────┐
│  WordGraphle – PT-BR                                    [_][□][X]│
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌─────────────────────────┐  ┌──────────────────────────┐ │
│  │   GRADE DE PALPITES     │  │  ┌─────┬─────┬─────┐     │ │
│  │   ┌───┬───┬───┬───┬───┐ │  │  │Letras│Grafo│Sugest│   │ │
│  │   │ C │ A │ R │ R │ O │ │  │  └─────┴─────┴─────┘     │ │
│  │   ├───┼───┼───┼───┼───┤ │  │                          │ │
│  │   │   │   │   │   │   │ │  │  [Teclado Visual]        │ │
│  │   ├───┼───┼───┼───┼───┤ │  │   Q W E R T Y U I O P   │ │
│  │   │   │   │   │   │   │ │  │    A S D F G H J K L    │ │
│  │   ├───┼───┼───┼───┼───┤ │  │     Z X C V B N M       │ │
│  │   │   │   │   │   │   │ │  │                          │ │
│  │   ├───┼───┼───┼───┼───┤ │  │  🟩 Confirmada           │ │
│  │   │   │   │   │   │   │ │  │  🟨 Existe               │ │
│  │   ├───┼───┼───┼───┼───┤ │  │  ⬜ Descartada           │ │
│  │   │   │   │   │   │   │ │  └──────────────────────────┘ │
│  │   └───┴───┴───┴───┴───┘ │                              │
│  └─────────────────────────┘                              │
│                                                               │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │ Digite: [_____________________________] [Enviar] [Novo] │ │
│  └─────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### Sistema de Cores

```mermaid
graph LR
    A[Letra digitada] --> B{Comparação}
    B -->|Posição e letra corretas| C[🟩 VERDE]
    B -->|Letra existe, posição errada| D[🟨 AMARELO]
    B -->|Letra não existe| E[⬜ CINZA]
    
    C --> F[Background: #6aaa64]
    D --> G[Background: #c9b458]
    E --> H[Background: #787c7e]
    
    style C fill:#6aaa64,color:#fff
    style D fill:#c9b458,color:#fff
    style E fill:#787c7e,color:#fff
```

---

## 🧮 Algoritmos e Lógica

### 1. Algoritmo de Avaliação de Palpite

```java
// Pseudocódigo simplificado
function evaluate(guess):
    1. Normalizar guess (remover acentos) → A-Z
    2. Verificar tamanho == L
    
    3. Contar letras na palavra secreta:
       remaining[letra] = quantidade
    
    4. PRIMEIRO PASSE (VERDE):
       para cada posição i:
           se guess[i] == secret[i]:
               colors[i] = GREEN
               remaining[guess[i]]--
    
    5. SEGUNDO PASSE (AMARELO/CINZA):
       para cada posição i não-verde:
           se remaining[guess[i]] > 0:
               colors[i] = YELLOW
               remaining[guess[i]]--
           senão:
               colors[i] = GRAY
    
    6. Atualizar Constraints baseado em colors
    7. Retornar Feedback(guess, colors)
```

### 2. Atualização de Constraints

```mermaid
flowchart TD
    A[Para cada letra do palpite] --> B{Cor?}
    
    B -->|🟩 GREEN| C[fixed[pos] = letra]
    B -->|🟨 YELLOW| D[bannedPos[pos][letra] = true]
    B -->|⬜ GRAY| E[Contagem atual da letra]
    
    C --> F[minCount[letra]++]
    D --> F
    
    E --> G{Letra tem GREEN/YELLOW?}
    G -->|Sim| H[maxCount[letra] = contagem]
    G -->|Não| I[maxCount[letra] = 0]
    
    F --> J[Constraints atualizados]
    H --> J
    I --> J
    
    style C fill:#90EE90
    style D fill:#FFD700
    style E fill:#D3D3D3
```

### 3. Filtragem de Candidatos

```java
function filterCandidates(dict, constraints):
    candidatos_validos = []
    
    para cada palavra w em dict:
        se isValid(w, constraints):
            adicionar w em candidatos_validos
    
    retornar candidatos_validos

function isValid(palavra, constraints):
    // Verificar restrições de posição
    para cada posição i:
        se constraints.fixed[i] != -1:
            se palavra[i] != constraints.fixed[i]:
                retornar false
        
        se constraints.bannedPos[i][palavra[i]]:
            retornar false
    
    // Verificar restrições de contagem
    contagem = contar_letras(palavra)
    
    para cada letra:
        se contagem[letra] < constraints.minCount[letra]:
            retornar false
        
        se constraints.maxCount[letra] >= 0:
            se contagem[letra] > constraints.maxCount[letra]:
                retornar false
    
    retornar true
```

---

## 📊 Visualização do Grafo

### Estrutura de Nós e Arestas

```mermaid
graph TB
    subgraph "Posição 0"
        C0[C<br/>freq: 0.85]
        A0[A<br/>freq: 0.62]
    end
    
    subgraph "Posição 1"
        A1[A<br/>freq: 0.78]
        O1[O<br/>freq: 0.45]
    end
    
    subgraph "Posição 2"
        R2[R<br/>freq: 0.91]
        S2[S<br/>freq: 0.34]
    end
    
    subgraph "Posição 3"
        R3[R<br/>freq: 0.66]
        T3[T<br/>freq: 0.53]
    end
    
    subgraph "Posição 4"
        O4[O<br/>freq: 0.88]
        A4[A<br/>freq: 0.72]
    end
    
    C0 -->|150| A1
    A0 -->|98| R2
    A1 -->|200| R2
    O1 -->|45| R2
    R2 -->|180| R3
    R2 -->|76| T3
    R3 -->|195| O4
    T3 -->|88| O4
    
    style C0 fill:#90EE90,stroke:#333,stroke-width:3px
    style A1 fill:#FFD700,stroke:#333,stroke-width:3px
    style R2 fill:#87CEEB,stroke:#333,stroke-width:3px
```

**Legenda:**
- **Nós**: Letras em cada posição com frequência normalizada
- **Arestas**: Peso = número de palavras com essa transição
- **Cores**: Indicam ativação baseada em constraints

---

## 🎯 Exemplo Completo de Jogo

```mermaid
sequenceDiagram
    participant U as Usuário
    participant G as GameEngine
    participant C as Constraints
    
    Note over G: Palavra secreta: MARCO
    
    U->>G: Palpite 1: "CARRO"
    G->>G: Avaliar
    G-->>U: ⬜🟩🟩🟩🟩
    G->>C: Atualizar: fixed[1..4], maxCount[C]=0
    
    U->>G: Palpite 2: "BARCO"
    G->>G: Avaliar
    G-->>U: ⬜🟩🟩⬜🟩
    G->>C: Atualizar: maxCount[B]=0, maxCount[C]=0
    
    U->>G: Palpite 3: "MARCO"
    G->>G: Avaliar
    G-->>U: 🟩🟩🟩🟩🟩
    Note over U,G: 🎉 VITÓRIA!
```

---

## 🔍 Detalhes Técnicos

### Normalização de Palavras

```java
// Exemplo de normalização
"café"   → "CAFE"
"maçã"   → "MACA"
"José"   → "JOSE"
"açúcar" → "ACUCAR"
```

**Processo:**
1. Aplicar NFD (decomposição Unicode)
2. Remover diacríticos combinados
3. Substituir 'ç' → 'c'
4. Remover não-letras
5. Converter para maiúsculas

### Estrutura de Dados do Grafo

```
wBase[pos][letraA][letraB] = peso da transição
active[pos][letra] = nó ativo após aplicar constraints

Exemplo:
wBase[0]['C'-'A']['A'-'A'] = 150  // 150 palavras começam com "CA"
active[0]['C'-'A'] = true          // 'C' permitido na posição 0
```

---

## 🚀 Fluxo de Execução Completo

```mermaid
stateDiagram-v2
    [*] --> Inicialização
    
    Inicialização --> CarregarDicionário
    CarregarDicionário --> ConstruirGrafo
    ConstruirGrafo --> SortearPalavra
    SortearPalavra --> AguardandoPalpite
    
    AguardandoPalpite --> ValidarEntrada
    ValidarEntrada --> AvaliarPalpite: Válido
    ValidarEntrada --> AguardandoPalpite: Inválido
    
    AvaliarPalpite --> AtualizarUI
    AtualizarUI --> AtualizarConstraints
    AtualizarConstraints --> FiltrarCandidatos
    FiltrarCandidatos --> GerarSugestões
    GerarSugestões --> VerificarVitória
    
    VerificarVitória --> Vitória: Acertou
    VerificarVitória --> VerificarTentativas: Não acertou
    
    VerificarTentativas --> AguardandoPalpite: Tentativas < MAX
    VerificarTentativas --> Derrota: Tentativas >= MAX
    
    Vitória --> NovoJogo
    Derrota --> NovoJogo
    NovoJogo --> SortearPalavra
    
    NovoJogo --> [*]: Sair
```

---

## 📈 Complexidade e Performance

### Complexidade Computacional

| Operação | Complexidade | Descrição |
|----------|-------------|-----------|
| Carregar Dicionário | O(N) | N = número de palavras |
| Construir Grafo | O(N × L) | Processar todas transições |
| Avaliar Palpite | O(L) | Comparar L letras |
| Filtrar Candidatos | O(N × L) | Verificar N palavras |
| Calcular Frequências | O(N × L) | Contar em todos candidatos |
| Sugerir Top K | O(N log K) | Ordenação parcial |

### Otimizações Implementadas

1. **Grafo Pré-computado**: Transições calculadas uma vez
2. **Filtragem Incremental**: Apenas candidatos válidos
3. **Normalização em Cache**: Mapa canônico → exibição
4. **Top-K Heap**: Evita ordenação completa

---

## 🎓 Conceitos Aplicados

### 1. Grafos Dirigidos Acíclicos (DAG)
- Estrutura em camadas para análise de palavras
- Arestas com pesos representam frequências

### 2. Programação Orientada a Objetos
- Separação de responsabilidades
- Encapsulamento de lógica complexa
- Reutilização de código

### 3. Padrões de Design
- **MVC**: Model (engine/model) - View (ui) - Controller (engine)
- **Strategy**: Diferentes métodos de sugestão
- **Observer**: Atualização de UI baseada em estado

### 4. Estruturas de Dados
- Arrays multidimensionais (grafo, constraints)
- Listas para candidatos
- Maps para normalização

### 5. Algoritmos
- Filtragem com predicados
- Ordenação por score
- Contagem de frequências

---

## 📝 Glossário

| Termo | Descrição |
|-------|-----------|
| **Palavra Canônica** | Forma normalizada A-Z sem acentos |
| **Palavra de Exibição** | Forma original com acentos |
| **Feedback** | Resultado de um palpite (cores) |
| **Constraints** | Restrições acumuladas do jogo |
| **Candidatos** | Palavras ainda possíveis |
| **Grafo em Camadas** | DAG com L camadas para L posições |
| **Transição** | Aresta entre letras em posições consecutivas |
| **Score** | Pontuação para ranking de sugestões |

---

## 🛠️ Tecnologias Utilizadas

- **Java 8+**: Linguagem principal
- **Swing**: Interface gráfica
- **Unicode Normalizer**: Tratamento de acentos
- **Streams API**: Filtragem e ordenação funcional

---

## 📦 Arquivos do Projeto

```
wordgraphle/
├── palavras.txt                    # Dicionário PT-BR (UTF-8)
├── WordGraphleApp.java            # Entry point
├── engine/
│   └── GameEngine.java            # Lógica do jogo
├── graph/
│   └── GraphModel.java            # Estrutura de grafo
├── model/
│   ├── Dictionary.java            # Carregamento de palavras
│   ├── Constraints.java           # Sistema de restrições
│   ├── Feedback.java              # Resultado de palpite
│   └── FeedbackColor.java         # Enum de cores
├── solver/
│   └── Solver.java                # Algoritmo de sugestões
└── ui/
    ├── WordGraphleFrame.java      # Janela principal
    ├── GraphPanel.java            # Visualização de grafo
    └── KeyboardPanel.java         # Teclado visual
```

---

## 🎯 Conclusão

O **WordGraphle** combina conceitos de:
- ✅ Teoria de Grafos
- ✅ Estruturas de Dados
- ✅ Algoritmos de Busca e Filtragem
- ✅ Interface Gráfica Rica
- ✅ Processamento de Linguagem Natural (normalização)

Criando uma experiência de jogo educativa e tecnicamente robusta! 🚀

---

**Desenvolvido com ❤️ em Java**
