// COMENTAR

package wordgraphle.solver;

import java.util.*;
import java.util.stream.Collectors;
import wordgraphle.graph.GraphModel;
import wordgraphle.model.*;

public class Solver {

    public static List<String> filterCandidates(List<String> dict, Constraints c) {
        return dict.stream().filter(w -> isValid(w, c)).collect(Collectors.toList());
    }

    private static boolean isValid(String w, Constraints c) {
        int L = c.L();
        int[] cnt = new int[26];

        for (int i=0;i<L;i++) {
            char ch = w.charAt(i);
            int id = Constraints.idx(ch);
            if (c.fixed[i] != -1 && c.fixed[i] != id) return false;
            if (c.bannedPos[i][id]) return false;
            cnt[id]++;
        }
        for (int id=0; id<26; id++) {
            if (cnt[id] < c.minCount[id]) return false;
            if (c.maxCount[id] >= 0 && cnt[id] > c.maxCount[id]) return false;
        }
        return true;
    }

    public static double[][] positionFrequencies(List<String> candidates, int L) {
        double[][] f = new double[L][26];
        if (candidates.isEmpty()) return f;
        for (String w : candidates) {
            for (int i=0;i<L;i++) {
                int id = Constraints.idx(w.charAt(i));
                f[i][id]++;
            }
        }
        for (int i=0;i<L;i++) {
            for (int id=0; id<26; id++) f[i][id] /= candidates.size();
        }
        return f;
    }

    // score simples: soma das freq. por posição + bônus de diversidade de letras
    public static double scoreWord(String w, double[][] freq) {
        double s = 0.0;
        boolean[] seen = new boolean[26];
        for (int i=0;i<w.length();i++) {
            int id = Constraints.idx(w.charAt(i));
            s += freq[i][id];
            if (!seen[id]) { s += 0.02; seen[id] = true; } // pequena penalização de repetição
        }
        return s;
    }

    public static List<String> suggestTop(List<String> candidates, int L, int k) {
        double[][] freq = positionFrequencies(candidates, L);
        return candidates.stream()
                .sorted((a,b) -> Double.compare(
                        scoreWord(b, freq), scoreWord(a, freq)))
                .limit(k)
                .collect(Collectors.toList());
    }

    /** NOVO: sugestão usando as frequências por posição vindas do grafo. */
    public static List<String> suggestTopUsingGraph(List<String> candidates, int L, GraphModel graph, int k) {
        double[][] freq = graph.positionLetterScores();
        return candidates.stream()
                .sorted((a,b) -> Double.compare(
                        scoreWord(b, freq), scoreWord(a, freq)))
                .limit(k)
                .collect(Collectors.toList());
    }
}