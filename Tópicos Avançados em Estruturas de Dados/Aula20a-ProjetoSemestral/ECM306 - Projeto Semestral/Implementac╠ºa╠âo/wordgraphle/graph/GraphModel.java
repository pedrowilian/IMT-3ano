package wordgraphle.graph;

import java.util.Arrays;
import wordgraphle.model.Constraints;
import wordgraphle.model.Dictionary;

/**
 * Grafo em camadas (DAG) para palavras de tamanho L.
 * Camadas: posições 0..L-1. Cada nó é (pos, letra 0..25).
 * Aresta dirigida (pos, a) -> (pos+1, b) com peso = nº de palavras
 * do dicionário que possuem essa transição nessas posições.
 *
 * As Constraints são aplicadas como uma máscara de nós ativos.
 * Observação: min/max globais por letra não são plenamente representáveis
 * localmente; por isso mantemos a filtragem exata por candidatos no Solver.
 */
public class GraphModel {
    private final int L;
    // Pesos base das transições: [pos][letraA][letraB]
    private final int[][][] wBase;
    // Máscara de letras ativas por posição (após aplicar Constraints)
    private final boolean[][] active;

    private GraphModel(int L) {
        this.L = L;
        this.wBase = new int[Math.max(0, L - 1)][26][26];
        this.active = new boolean[L][26];
        for (int i = 0; i < L; i++) Arrays.fill(this.active[i], true);
    }

    /** Constrói o grafo a partir do dicionário. */
    public static GraphModel fromDictionary(Dictionary dict) {
        GraphModel g = new GraphModel(dict.L());
        int L = dict.L();
        for (String w : dict.words()) {
            for (int pos = 0; pos < L - 1; pos++) {
                int a = w.charAt(pos)     - 'A';
                int b = w.charAt(pos + 1) - 'A';
                if (a >= 0 && a < 26 && b >= 0 && b < 26) {
                    g.wBase[pos][a][b]++;
                }
            }
        }
        return g;
    }

    /** Aplica as restrições de posição/letra como máscara de nós ativos. */
    public void applyConstraints(Constraints c) {
        for (int pos = 0; pos < L; pos++) {
            Arrays.fill(active[pos], true);
            if (c.fixed[pos] != -1) {
                int keep = c.fixed[pos];
                Arrays.fill(active[pos], false);
                active[pos][keep] = true;
            } else {
                for (int letter = 0; letter < 26; letter++) {
                    if (c.bannedPos[pos][letter]) active[pos][letter] = false;
                }
            }
        }
    }

    /** Peso da transição (pos, a) -> (pos+1, b), respeitando a máscara ativa. */
    public int edgeWeight(int pos, int a, int b) {
        if (pos < 0 || pos >= L - 1) return 0;
        if (!active[pos][a] || !active[pos + 1][b]) return 0;
        return wBase[pos][a][b];
    }

    /**
     * "Frequência" aproximada de letras por posição, derivada do grafo:
     * - pos 0: soma de saídas
     * - pos intermediárias: média(entradas, saídas)
     * - pos L-1: soma de entradas
     * Normaliza por posição para virar probabilidades (~[0..1]).
     */
    public double[][] positionLetterScores() {
        double[][] score = new double[L][26];
        if (L == 0) return score;

        // pos 0: usa saídas
        if (L >= 2) {
            for (int a = 0; a < 26; a++) {
                if (!active[0][a]) continue;
                int sum = 0;
                for (int b = 0; b < 26; b++) sum += edgeWeight(0, a, b);
                score[0][a] = sum;
            }
        }

        // pos intermediárias
        for (int pos = 1; pos < L - 1; pos++) {
            for (int x = 0; x < 26; x++) {
                if (!active[pos][x]) continue;
                int in = 0, out = 0;
                for (int a = 0; a < 26; a++) in  += edgeWeight(pos - 1, a, x);
                for (int b = 0; b < 26; b++) out += edgeWeight(pos,     x, b);
                score[pos][x] = (in + out) / 2.0;
            }
        }

        // pos L-1: entradas
        if (L >= 2) {
            int pos = L - 1;
            for (int b = 0; b < 26; b++) {
                if (!active[pos][b]) continue;
                int sum = 0;
                for (int a = 0; a < 26; a++) sum += edgeWeight(pos - 1, a, b);
                score[pos][b] = sum;
            }
        }

        // normaliza por posição
        for (int pos = 0; pos < L; pos++) {
            double tot = 0;
            for (int l = 0; l < 26; l++) tot += score[pos][l];
            if (tot > 0) for (int l = 0; l < 26; l++) score[pos][l] /= tot;
        }
        return score;
    }

    public int L() { return L; }
    public boolean[][] activeMask() { return active; }
    public int[][][] baseWeights() { return wBase; }
}