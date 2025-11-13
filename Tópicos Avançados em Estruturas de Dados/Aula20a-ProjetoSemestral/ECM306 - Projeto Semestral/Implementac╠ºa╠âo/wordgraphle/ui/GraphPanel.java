// COMENTAR

package wordgraphle.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import wordgraphle.graph.GraphModel;

/**
 * Painel para desenhar o grafo em camadas do WordGraphle.
 * Mostra, por posição, as letras ativas mais relevantes e as arestas entre camadas
 * com espessura proporcional ao peso.
 */
public class GraphPanel extends JPanel {
    private GraphModel graph;
    // Limita a quantidade de letras por coluna para manter legível
    private static final int MAX_LETTERS_PER_COL = 12;
    private static final int NODE_RADIUS = 8;
    private static final int MARGIN_H = 40;
    private static final int MARGIN_V = 30;

    public GraphPanel() {
        setBackground(new Color(24, 24, 24));
        setPreferredSize(new Dimension(600, 270));
        setMinimumSize(new Dimension(400, 240));
    }

    public void setGraphModel(GraphModel graph) {
        this.graph = graph;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (graph == null || graph.L() == 0) {
            drawCenteredText(g, "Grafo indisponível", getWidth(), getHeight());
            return;
        }

        int L = graph.L();
        double[][] scores = graph.positionLetterScores();
        boolean[][] active = graph.activeMask();

        // Para cada posição, escolhe as letras ativas mais relevantes
        List<int[]> lettersPerPos = new ArrayList<>(L); // cada item = array de letras (indices 0..25)
        for (int pos = 0; pos < L; pos++) {
            final int p = pos; 

            List<Integer> candidates = new ArrayList<>();
            for (int l = 0; l < 26; l++) {
                if (active[p][l] && scores[p][l] > 0.0) candidates.add(l);
            }
            // se nada via score, considera ativas sem score
            if (candidates.isEmpty()) {
                for (int l = 0; l < 26; l++) {
                    if (active[p][l]) candidates.add(l);
                }
            }
            // ordena por score desc e corta
            candidates.sort(Comparator.comparingDouble((Integer l) -> scores[p][l]).reversed());
            if (candidates.size() > MAX_LETTERS_PER_COL) {
                candidates = candidates.subList(0, MAX_LETTERS_PER_COL);
            }
            int[] arr = new int[candidates.size()];
            for (int i = 0; i < candidates.size(); i++) arr[i] = candidates.get(i);
            lettersPerPos.add(arr);
        }

        // Calcula X/Y dos nós
        int availW = getWidth() - 2 * MARGIN_H;
        int availH = getHeight() - 2 * MARGIN_V;
        int colCount = Math.max(1, L);
        double dx = (colCount == 1) ? 0 : (double) availW / (colCount - 1);

        // Y por coluna depende do nº de nós selecionados
        List<int[]> nodeY = new ArrayList<>(L); // cada item: y por índice local
        for (int pos = 0; pos < L; pos++) {
            int n = lettersPerPos.get(pos).length;
            int[] ys = new int[n];
            if (n == 0) {
                nodeY.add(ys);
                continue;
            }
            double step = (n == 1) ? 0 : (double) availH / (n - 1);
            for (int i = 0; i < n; i++) {
                ys[i] = (int) Math.round(MARGIN_V + i * step);
            }
            nodeY.add(ys);
        }

        // Descobre o peso máximo entre arestas desenhadas para normalizar espessura
        int maxW = 0;
        for (int pos = 0; pos < L - 1; pos++) {
            int[] left = lettersPerPos.get(pos);
            int[] right = lettersPerPos.get(pos + 1);
            for (int i = 0; i < left.length; i++) {
                for (int j = 0; j < right.length; j++) {
                    int w = graph.edgeWeight(pos, left[i], right[j]);
                    if (w > maxW) maxW = w;
                }
            }
        }
        if (maxW == 0) {
            drawCenteredText(g, "Sem transições ativas (verifique as restrições)", getWidth(), getHeight());
            // ainda desenha nós
        }

        // Desenha arestas
        if (L >= 2) {
            for (int pos = 0; pos < L - 1; pos++) {
                int x1 = (int) Math.round(MARGIN_H + pos * dx);
                int x2 = (int) Math.round(MARGIN_H + (pos + 1) * dx);
                int[] left = lettersPerPos.get(pos);
                int[] right = lettersPerPos.get(pos + 1);
                int[] yLeft = nodeY.get(pos);
                int[] yRight = nodeY.get(pos + 1);

                for (int i = 0; i < left.length; i++) {
                    for (int j = 0; j < right.length; j++) {
                        int w = graph.edgeWeight(pos, left[i], right[j]);
                        if (w <= 0) continue;
                        float t = (float) Math.max(0.05, Math.min(1.0, w / (double) maxW));
                        float stroke = 0.6f + 4.0f * t; // espessura 0.6..4.6
                        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f + 0.6f * t);
                        g.setComposite(ac);
                        g.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        g.setColor(new Color(120, 170, 255));
                        g.drawLine(x1, yLeft[i], x2, yRight[j]);
                    }
                }
            }
            g.setComposite(AlphaComposite.SrcOver);
        }

        // Desenha nós
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        FontMetrics fm = g.getFontMetrics();
        for (int pos = 0; pos < L; pos++) {
            int x = (int) Math.round(MARGIN_H + pos * dx);
            int[] letters = lettersPerPos.get(pos);
            int[] ys = nodeY.get(pos);
            for (int i = 0; i < letters.length; i++) {
                int y = ys[i];
                // bolinha
                g.setColor(new Color(50, 50, 50));
                g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
                g.setColor(new Color(200, 200, 200));
                g.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

                // letra
                char ch = (char) ('A' + letters[i]);
                String s = String.valueOf(ch);
                int tw = fm.stringWidth(s);
                g.drawString(s, x - tw / 2, y - NODE_RADIUS - 6); // label acima do nó
            }

            // legenda posição
            g.setColor(new Color(180, 180, 180));
            String ptxt = "pos " + pos;
            int tw = fm.stringWidth(ptxt);
            g.drawString(ptxt, x - tw / 2, getHeight() - 8);
        }
    }

    private void drawCenteredText(Graphics2D g, String msg, int w, int h) {
        g.setColor(new Color(180, 180, 180));
        FontMetrics fm = g.getFontMetrics();
        int tw = fm.stringWidth(msg);
        int th = fm.getHeight();
        g.drawString(msg, (w - tw) / 2, (h + th) / 2);
    }
}
