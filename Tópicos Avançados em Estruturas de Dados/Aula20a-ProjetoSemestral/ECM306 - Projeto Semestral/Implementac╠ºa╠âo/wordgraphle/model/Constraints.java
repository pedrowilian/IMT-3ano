package wordgraphle.model;               

import java.util.Arrays;

/*
 * Restrição:
 * 1) Restrição por posição: letra fixa em uma posição específica (verde)
 * 2) Proibição por posição: letra não pode aparecer em determinada posição (amarelo/cinza aplicados localmente)
 * 3) Restrição global de contagem: mínimo/máximo do número de ocorrências de cada letra na palavra inteira
 *    (tratar letras repetidas corretamente)
 *
 * Convenções:
 * - Alfabeto considerado: 26 letras A..Z (após normalização do dicionário)
 * - Valor sentinela para "sem fixo" em uma posição: -1
 * - Valor sentinela para "sem limite máximo" de uma letra: -1
 */

public class Constraints {          

    private final int L;
    public final int[] fixed;
    public final boolean[][] bannedPos;
    public final int[] minCount;
    public final int[] maxCount;

    /*
     *Construtor com o conjunto de restrições para palavras de tamanho L
     * - 'fixed' com -1 (nenhuma posição fixada);
     * - 'bannedPos' como matriz L×26 de false (nada proibido inicialmente);
     * - 'minCount' com 0 (nenhuma exigência mínima);
     * - 'maxCount' com -1 (nenhum teto definido).
     */
    public Constraints(int L) {                 
        this.L = L;                  

        this.fixed = new int[L];                 // Aloca vetor de fixos por posição
        Arrays.fill(this.fixed, -1);             // Define -1 em todas as posições (sem letra fixa)

        this.bannedPos = new boolean[L][26];     // Aloca matriz L × 26; default false (nada proibido)

        this.minCount = new int[26];             // Aloca vetor de mínimos por letra; default 0 (nenhum mínimo)
        this.maxCount = new int[26];             // Aloca vetor de máximos por letra

        Arrays.fill(this.maxCount, -1);          // Define -1 em todos os máximos (sem limite ainda)
    }

    //Getter para L - retornar o comprimento
    public int L() {                              
        return L;                                 
    }

    /*
     * Converte um caractere de letra (A..Z ou a..z) para o índice 0..25
     * Caso o caractere não esteja no intervalo alfabético, retorna -1
     */
    public static int idx(char c) {      
        // Converte para maiúscula para padronização        
        char u = Character.toUpperCase(c);   

        // Verifica se está fora do intervalo A..Z; se sim, retorna -1
        if (u < 'A' || u > 'Z') 
            return -1;      
        // Converte 'A'..'Z' para 0..25 por aritmética de caracteres 
        else
            return u - 'A';                          
    }
}