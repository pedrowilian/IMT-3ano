package wordgraphle.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import wordgraphle.engine.GameEngine;
import wordgraphle.graph.GraphModel;
import wordgraphle.model.Dictionary;
import wordgraphle.model.Feedback;
import wordgraphle.model.FeedbackColor;
import wordgraphle.solver.Solver;

/**
 * Janela principal do jogo com interface moderna inspirada no Wordle/Termo.
 * - Layout vertical: Grade de palavras no topo, teclado virtual embaixo
 * - Design minimalista com cores escuras e células arredondadas
 * - Campo de entrada integrado ao layout
 */
public class WordGraphleFrame extends JFrame {
    private static final int SUGGESTION_K = 30;

    private final int L, MAX_TRIES;

    private GameEngine engine;

    // Grafo e visualização
    private GraphModel graph;
    private GraphPanel graphPanel;

    // UI: grade, entrada e controles
    private JLabel[][] grid;   // MAX_TRIES x L
    private JTextField input;
    private JButton btnSubmit, btnNew;

    // Teclado (estado das letras)
    private KeyboardPanel keyboardPanel;

    // Sugestões
    private DefaultListModel<String> suggestionsModel;

    private int row = 0;
    
    // Cores do tema escuro moderno
    private static final Color BG_MAIN = new Color(18, 18, 19);
    private static final Color BG_CELL_EMPTY = new Color(18, 18, 19);
    private static final Color BORDER_CELL_EMPTY = new Color(58, 58, 60);
    private static final Color BORDER_CELL_FILLED = new Color(86, 87, 88);

    public WordGraphleFrame(int L, int maxTries) {
        super("WordGraphle – PT-BR");
        this.L = L;
        this.MAX_TRIES = maxTries;

        // 1) Carrega dicionário automaticamente do classpath
        Dictionary dict = tryAutoLoadDictionary(L).orElse(null);
        if (dict == null) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível localizar o arquivo 'palavras.txt' no classpath.\n" +
                    "Coloque-o em '/palavras.txt' ou '/wordgraphle/palavras.txt' e reinicie.",
                    "Dicionário ausente", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        // 2) Inicializa motor e grafo
        this.engine = new GameEngine(dict);
        this.graph  = GraphModel.fromDictionary(dict);
        this.graph.applyConstraints(engine.constraints());

        // 3) Montagem da interface moderna
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 1000);  // Aumentado para melhor visualização
        setLocationRelativeTo(null);
        
        // Define o ícone da janela
        try {
            // Tenta carregar de diferentes localizações
            java.net.URL iconURL = getClass().getResource("/wordgraphle/image_wordgraphle.png");
            if (iconURL == null) {
                iconURL = getClass().getResource("/image_wordgraphle.png");
            }
            if (iconURL != null) {
                BufferedImage icon = ImageIO.read(iconURL);
                setIconImage(icon);
            } else {
                System.err.println("Aviso: Não foi possível encontrar o arquivo de ícone.");
            }
        } catch (IOException e) {
            System.err.println("Aviso: Não foi possível carregar o ícone: " + e.getMessage());
        }
        
        // Painel principal com fundo escuro
        JPanel root = new JPanel(new BorderLayout(0, 15));
        root.setBackground(BG_MAIN);
        root.setBorder(new EmptyBorder(15, 20, 15, 20));
        setContentPane(root);

        // === CABEÇALHO ===
        JPanel header = new JPanel(new GridBagLayout());
        header.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Espaço vazio à esquerda (para balancear o botão à direita)
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        JPanel leftSpacer = new JPanel();
        leftSpacer.setOpaque(false);
        leftSpacer.setPreferredSize(new Dimension(85, 30)); // Mesmo tamanho do botão
        header.add(leftSpacer, gbc);
        
        // Painel central com título + imagem
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        titlePanel.setOpaque(false);
        
        JLabel title = new JLabel("WORDGRAPHLE");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        titlePanel.add(title);
        
        // Adiciona a imagem ao lado do título
        try {
            java.net.URL iconURL = getClass().getResource("/wordgraphle/image_wordgraphle.png");
            if (iconURL == null) {
                iconURL = getClass().getResource("/image_wordgraphle.png");
            }
            if (iconURL != null) {
                BufferedImage originalIcon = ImageIO.read(iconURL);
                // Redimensiona a imagem para 40x40 pixels para ficar proporcional ao título
                Image scaledIcon = originalIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon));
                titlePanel.add(iconLabel);
            }
        } catch (IOException e) {
            System.err.println("Aviso: Não foi possível carregar a imagem do título: " + e.getMessage());
        }
        
        header.add(titlePanel, gbc);
        
        // Botão à direita
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        btnNew = new JButton("Novo Jogo");
        btnNew.setFont(new Font("Arial", Font.PLAIN, 11));
        btnNew.setFocusPainted(false);
        btnNew.setBackground(new Color(83, 141, 78));
        btnNew.setForeground(Color.WHITE);
        btnNew.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.add(btnNew, gbc);
        
        root.add(header, BorderLayout.NORTH);

        // === ÁREA CENTRAL: Grade de palavras ===
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        
        JPanel gridPanel = new JPanel(new GridLayout(MAX_TRIES, L, 6, 6));
        gridPanel.setOpaque(false);
        grid = new JLabel[MAX_TRIES][L];
        
        for (int r = 0; r < MAX_TRIES; r++) {
            for (int c = 0; c < L; c++) {
                RoundedCell cell = new RoundedCell(" ");
                cell.setPreferredSize(new Dimension(55, 55));
                cell.setMinimumSize(new Dimension(55, 55));
                cell.setMaximumSize(new Dimension(55, 55));
                grid[r][c] = cell;
                gridPanel.add(cell);
            }
        }
        centerPanel.add(gridPanel);
        
        root.add(centerPanel, BorderLayout.CENTER);
        
        // === PAINEL DE ENTRADA: Entre a grade e o teclado ===
        JPanel inputContainer = new JPanel();
        inputContainer.setLayout(new BoxLayout(inputContainer, BoxLayout.Y_AXIS));
        inputContainer.setOpaque(false);
        inputContainer.setBorder(new EmptyBorder(15, 20, 10, 20));
        
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        inputPanel.setOpaque(false);
        
        input = new JTextField(L + 2);
        input.setFont(new Font("Arial", Font.BOLD, 20));
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setBackground(new Color(40, 40, 42));
        input.setForeground(Color.WHITE);
        input.setCaretColor(Color.WHITE);
        input.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(121, 184, 255), 3),  // Borda azul destacada
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        input.setPreferredSize(new Dimension(180, 45));
        
        btnSubmit = new JButton("ENVIAR");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 13));
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBackground(new Color(83, 141, 78));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubmit.setPreferredSize(new Dimension(90, 45));
        
        inputPanel.add(input);
        inputPanel.add(btnSubmit);
        inputContainer.add(inputPanel);

        // === TECLADO VIRTUAL ===
        keyboardPanel = new KeyboardPanel();
        
        // Painel que contém entrada + teclado
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 0));
        bottomPanel.setOpaque(false);
        bottomPanel.add(inputContainer, BorderLayout.NORTH);
        bottomPanel.add(keyboardPanel, BorderLayout.CENTER);
        
        root.add(bottomPanel, BorderLayout.SOUTH);
        
        // Inicializa componentes de grafo e sugestões (ocultos, mas disponíveis via menu)
        graphPanel = new GraphPanel();
        graphPanel.setGraphModel(graph);
        suggestionsModel = new DefaultListModel<>();

        // Ações
        btnSubmit.addActionListener(e -> onSubmit());
        input.addActionListener(e -> onSubmit());
        btnNew.addActionListener(e -> newGame());
        
        // Menu para acessar recursos avançados
        createMenuBar();

        refreshSuggestions();
    }
    
    /** Cria menu com opções avançadas (Grafo, Sugestões, Sobre). */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuTools = new JMenu("Ferramentas");
        
        JMenuItem itemGraph = new JMenuItem("Ver Grafo");
        itemGraph.addActionListener(e -> showGraphDialog());
        menuTools.add(itemGraph);
        
        JMenuItem itemSuggestions = new JMenuItem("Ver Sugestões");
        itemSuggestions.addActionListener(e -> showSuggestionsDialog());
        menuTools.add(itemSuggestions);
        
        menuTools.addSeparator();
        
        JMenuItem itemAbout = new JMenuItem("Sobre");
        itemAbout.addActionListener(e -> showAboutDialog());
        menuTools.add(itemAbout);
        
        menuBar.add(menuTools);
        setJMenuBar(menuBar);
    }
    
    /** Mostra diálogo com visualização do grafo. */
    private void showGraphDialog() {
        JDialog dialog = new JDialog(this, "Visualização do Grafo", false);
        dialog.setSize(700, 600);
        dialog.setLocationRelativeTo(this);
        dialog.add(new JScrollPane(graphPanel));
        dialog.setVisible(true);
    }
    
    /** Mostra diálogo com sugestões. */
    private void showSuggestionsDialog() {
        JDialog dialog = new JDialog(this, "Sugestões de Palavras", false);
        dialog.setSize(450, 600);
        dialog.setLocationRelativeTo(this);
        
        // Painel principal com fundo escuro
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(BG_MAIN);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Cabeçalho com informações
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Palavras Possíveis");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel countLabel = new JLabel();
        countLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        countLabel.setForeground(new Color(180, 180, 180));
        countLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        // Atualiza contagem
        int totalCandidates = Solver.filterCandidates(engine.words(), engine.constraints()).size();
        countLabel.setText(totalCandidates + " candidato(s) encontrado(s)");
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(countLabel, BorderLayout.EAST);
        
        // Lista de sugestões com estilo moderno
        JList<String> list = new JList<>(suggestionsModel);
        list.setFont(new Font("Consolas", Font.BOLD, 18));
        list.setBackground(new Color(30, 30, 32));
        list.setForeground(Color.WHITE);
        list.setSelectionBackground(new Color(83, 141, 78));
        list.setSelectionForeground(Color.WHITE);
        list.setFixedCellHeight(40);
        list.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        
        // ScrollPane customizado
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(58, 58, 60), 1));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Rodapé com dica
        JLabel hintLabel = new JLabel("💡 Palavras ordenadas por probabilidade");
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        hintLabel.setForeground(new Color(150, 150, 150));
        hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(hintLabel, BorderLayout.SOUTH);
        
        dialog.getContentPane().setBackground(BG_MAIN);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    /** Mostra diálogo "Sobre". */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
            "WordGraphle - Versão 1.0\n\n" +
            "Jogo de palavras inspirado no Wordle/Termo\n" +
            "com análise de grafo e sugestões inteligentes.\n\n" +
            "Desenvolvido em Java com Swing.",
            "Sobre o WordGraphle",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /** Carrega dicionário embutido: tenta "/palavras.txt" e "/wordgraphle/palavras.txt". */
    private Optional<Dictionary> tryAutoLoadDictionary(int L) {
        try {
            return Optional.of(Dictionary.loadFromResource("/palavras.txt", L));
        } catch (Exception ignore) { }
        try {
            return Optional.of(Dictionary.loadFromResource("/wordgraphle/palavras.txt", L));
        } catch (Exception ignore) { }
        return Optional.empty();
    }

    /** Trata o envio do palpite e atualiza UI (grade, teclado, grafo, sugestões). */
    private void onSubmit() {
        if (row >= MAX_TRIES) return;

        String raw = input.getText().trim();
        if (raw.isEmpty()) return;

        String canon = Dictionary.normalize(raw);
        if (canon.length() != L) {
            JOptionPane.showMessageDialog(this, "Digite uma palavra de " + L + " letras (acentos serão ignorados).");
            return;
        }

        // Verifica se a palavra existe no dicionário
        if (!engine.words().contains(canon)) {
            JOptionPane.showMessageDialog(this, 
                "A palavra '" + raw + "' não existe no dicionário.\n" +
                "Por favor, digite uma palavra válida no nosso dicionário!",
                "Palavra Inválida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Feedback fb = engine.evaluate(raw); // normaliza internamente e gera feedback
            paintRow(row, fb);
            keyboardPanel.applyFeedback(fb);    // atualiza estados das letras
            row++;

            graph.applyConstraints(engine.constraints());
            graphPanel.repaint();

            refreshSuggestions();

            if (fb.guess.equals(engine.secret())) {
                JOptionPane.showMessageDialog(this, "Acertou! A palavra era: " + engine.secretDisplay());
                lock();
            } else if (row == MAX_TRIES) {
                JOptionPane.showMessageDialog(this, "Fim de jogo. A palavra era: " + engine.secretDisplay());
                lock();
            }

            input.setText("");
            input.requestFocusInWindow();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    /** Pinta a linha r da grade com o feedback retornado pelo motor. */
    private void paintRow(int r, Feedback fb) {
        for (int i = 0; i < L; i++) {
            JLabel cell = grid[r][i];
            char ch = fb.guess.charAt(i); // canônico (A–Z)
            cell.setText(String.valueOf(ch));
            cell.setBackground(FeedbackColor.toAwt(fb.colors[i]));
            cell.setForeground(Color.WHITE);
            
            // Adiciona borda mais grossa quando preenchida
            if (cell instanceof RoundedCell) {
                ((RoundedCell) cell).setBorderColor(BORDER_CELL_FILLED);
            }
        }
    }

    /** Atualiza a lista de sugestões conforme as restrições acumuladas. */
    private void refreshSuggestions() {
        List<String> candidates = Solver.filterCandidates(engine.words(), engine.constraints());
        // Usa sempre o método baseado em grafo para melhores sugestões
        List<String> top = Solver.suggestTopUsingGraph(candidates, L, graph, SUGGESTION_K);

        suggestionsModel.clear();
        if (candidates.isEmpty()) {
            suggestionsModel.addElement("(Sem candidatos — verifique o dicionário/regras)");
            return;
        }
        for (String w : top) suggestionsModel.addElement(w);
    }

    /** Inicia um novo jogo com a mesma lista; limpa grade, reseta teclado e reabilita entrada. */
    private void newGame() {
        engine.pickNewSecret();

        // Limpa a grade
        for (int r = 0; r < MAX_TRIES; r++) {
            for (int c = 0; c < L; c++) {
                JLabel cell = grid[r][c];
                cell.setText(" ");
                cell.setBackground(BG_CELL_EMPTY);
                cell.setForeground(Color.WHITE);
                
                // Restaura borda original
                if (cell instanceof RoundedCell) {
                    ((RoundedCell) cell).setBorderColor(BORDER_CELL_EMPTY);
                }
            }
        }
        row = 0;
        keyboardPanel.reset();

        // Reaplica restrições no grafo
        graph.applyConstraints(engine.constraints());
        graphPanel.repaint();

        // Reabilita entrada (corrige o bug reportado)
        input.setEnabled(true);
        btnSubmit.setEnabled(true);
        input.setText("");
        input.requestFocusInWindow();

        refreshSuggestions();
    }

    /** Desabilita entrada após acerto ou esgotar tentativas. */
    private void lock() {
        btnSubmit.setEnabled(false);
        input.setEnabled(false);
    }

    // =========================================================================================
    //                                RoundedCell (célula com cantos arredondados)
    // =========================================================================================
    /**
     * Componente de célula customizado com cantos arredondados para a grade.
     */
    private static final class RoundedCell extends JLabel {
        private Color borderColor = new Color(58, 58, 60);
        private static final int ARC = 8;
        
        RoundedCell(String text) {
            super(text, SwingConstants.CENTER);
            setOpaque(false);
            setFont(new Font("Arial", Font.BOLD, 32));
            setForeground(Color.WHITE);
            setBackground(new Color(18, 18, 19));
        }
        
        void setBorderColor(Color c) {
            this.borderColor = c;
            repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Fundo arredondado
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
            
            // Borda arredondada
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, ARC, ARC);
            
            // Texto
            super.paintComponent(g2);
            g2.dispose();
        }
    }

    // =========================================================================================
    //                                KeyboardPanel (teclado estilizado)
    // =========================================================================================
    /**
     * Painel que mostra as letras já usadas e seu estado:
     * 0 = desconhecida, 1 = GRAY, 2 = YELLOW, 3 = GREEN (o estado só "sobe").
     * Layout QWERTY em 3 linhas com offsets para melhor estética.
     */
    private static final class KeyboardPanel extends JPanel {

        /** Teclas visíveis (A..Z). */
        private final KeyLabel[] key = new KeyLabel[26];

        /** Estado de cada letra (0..3). */
        private final int[] state = new int[26];

        /** Cores base. */
        private static final Color BG_PANEL   = new Color(18, 18, 19);
        private static final Color BG_UNKNOWN = new Color(129, 131, 132);
        private static final Color FG_TEXT    = Color.WHITE;

        /** Tamanho das teclas e gaps. */
        private static final int KEY_W = 44;
        private static final int KEY_H = 58;
        private static final int H_GAP = 6;
        private static final int V_GAP = 8;
        private static final int ARC   = 4;

        KeyboardPanel() {
            setOpaque(true);
            setBackground(BG_PANEL);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(8, 10, 15, 10));

            // Linha 1: QWERTYUIOP
            add(buildRow("QWERTYUIOP", 0));
            add(Box.createVerticalStrut(V_GAP));

            // Linha 2: ASDFGHJKL (com offset)
            add(buildRow("ASDFGHJKL", KEY_W / 2));
            add(Box.createVerticalStrut(V_GAP));

            // Linha 3: ZXCVBNM com botões especiais
            JPanel row3Container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            row3Container.setOpaque(false);
            
            // Botão ENTER à esquerda
            KeyLabel enterKey = new KeyLabel("ENTER");
            enterKey.setPreferredSize(new Dimension(65, KEY_H));
            enterKey.setFont(enterKey.getFont().deriveFont(Font.BOLD, 12f));
            enterKey.setBg(BG_UNKNOWN);
            row3Container.add(enterKey);
            
            row3Container.add(Box.createHorizontalStrut(H_GAP));
            
            // Letras Z-M
            for (char ch : "ZXCVBNM".toCharArray()) {
                int id = ch - 'A';
                KeyLabel k = new KeyLabel(String.valueOf(ch));
                k.setPreferredSize(new Dimension(KEY_W, KEY_H));
                k.setFont(k.getFont().deriveFont(Font.BOLD, 16f));
                k.setForeground(FG_TEXT);
                k.setBg(BG_UNKNOWN);
                key[id] = k;
                row3Container.add(k);
                row3Container.add(Box.createHorizontalStrut(H_GAP));
            }
            
            // Botão BACKSPACE à direita
            KeyLabel backKey = new KeyLabel("⌫");
            backKey.setPreferredSize(new Dimension(65, KEY_H));
            backKey.setFont(backKey.getFont().deriveFont(Font.BOLD, 20f));
            backKey.setBg(BG_UNKNOWN);
            row3Container.add(backKey);
            
            add(row3Container);
        }

        /** Cria uma linha de teclas com um offset inicial para imitar o teclado do Termo. */
        private JPanel buildRow(String letters, int leftOffsetPx) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, H_GAP, 0));
            row.setOpaque(false);

            if (leftOffsetPx > 0) {
                // "empurra" o início das teclas para a direita criando o offset
                row.add(Box.createRigidArea(new Dimension(leftOffsetPx, 1)));
            }

            for (char ch : letters.toCharArray()) {
                int id = ch - 'A';
                KeyLabel k = new KeyLabel(String.valueOf(ch));
                k.setPreferredSize(new Dimension(KEY_W, KEY_H));
                k.setFont(k.getFont().deriveFont(Font.BOLD, 18f));
                k.setForeground(FG_TEXT);
                k.setBg(BG_UNKNOWN);
                key[id] = k;
                row.add(k);
            }
            return row;
        }

        /** Aplica um feedback: atualiza estados e repinta cores das teclas. */
        void applyFeedback(Feedback fb) {
            for (int i = 0; i < fb.colors.length; i++) {
                int id = fb.guess.charAt(i) - 'A';
                switch (fb.colors[i]) {
                    case GREEN  -> state[id] = 3;
                    case YELLOW -> { if (state[id] < 2) state[id] = 2; }
                    case GRAY   -> { if (state[id] < 1) state[id] = 1; }
                }
            }
            repaintStates();
        }

        /** Reseta o teclado para um novo jogo. */
        void reset() {
            for (int i = 0; i < 26; i++) state[i] = 0;
            repaintStates();
        }

        /** Atualiza a cor de fundo das teclas de acordo com o estado atual. */
        private void repaintStates() {
            for (int id = 0; id < 26; id++) {
                KeyLabel lb = key[id];
                if (lb == null) continue;
                switch (state[id]) {
                    case 3 -> lb.setBg(FeedbackColor.toAwt(FeedbackColor.GREEN));
                    case 2 -> lb.setBg(FeedbackColor.toAwt(FeedbackColor.YELLOW));
                    case 1 -> lb.setBg(FeedbackColor.toAwt(FeedbackColor.GRAY));
                    default -> lb.setBg(BG_UNKNOWN);
                }
            }
            revalidate();
            repaint();
        }

        // ---------- Componente de tecla com cantos arredondados ----------
        private static final class KeyLabel extends JLabel {
            private Color bg = BG_UNKNOWN;

            KeyLabel(String text) {
                super(text, SwingConstants.CENTER);
                setOpaque(false); // nós mesmos pintamos o fundo
                setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
            }

            void setBg(Color c) {
                if (c != null && !c.equals(this.bg)) {
                    this.bg = c;
                    repaint();
                }
            }

            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // fundo arredondado
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);

                // texto
                super.paintComponent(g2);
                g2.dispose();
            }
        }
    }
}
