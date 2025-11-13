// COMENTAR

package wordgraphle.engine;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import wordgraphle.model.Constraints;
import wordgraphle.model.Dictionary;
import wordgraphle.model.Feedback;
import wordgraphle.model.FeedbackColor;

public class GameEngine {

    // ---------- Estado principal ----------
    private final Dictionary dict;       // Dicionário carregado (palavras canônicas e mapeamento de exibição)
    private final Random rng = new Random();

    private String secret;               // Palavra secreta canônica (A–Z, sem acento) — usada na lógica
    private String secretDisplay;        // Palavra secreta para exibição (com acento), vinda do dicionário

    private final Constraints constraints; // Restrições acumuladas a partir dos palpites

    // ---------- Construtor ----------
    /**
     * Cria o motor para o dicionário informado e já inicia um jogo novo.
     * @param dict dicionário carregado (mesmo L em todo o jogo)
     */
    public GameEngine(Dictionary dict) {
        this.dict = dict;
        this.constraints = new Constraints(dict.L());
        pickNewSecret();
    }

    // ---------- Acesso de estado ----------
    /** Palavra secreta canônica (sem acento). */
    public String secret() { return secret; }

    /** Palavra secreta para exibição (com acento). */
    public String secretDisplay() { return secretDisplay; }

    /** Restrições acumuladas. */
    public Constraints constraints() { return constraints; }

    /** Tamanho fixo das palavras. */
    public int L() { return dict.L(); }

    /** Lista de palavras canônicas (permitidas). */
    public List<String> words() { return dict.words(); }

    // ---------- Fluxo do jogo ----------
    /** Sorteia uma nova palavra secreta e reseta todas as restrições.*/
    public void pickNewSecret() {
        List<String> w = dict.words();                    // lista canônica
        this.secret = w.get(rng.nextInt(w.size()));       // escolhe uma canônica
        this.secretDisplay = dict.displayOf(this.secret); // forma exibível (com acento)

        // Zera restrições
        int L = constraints.L();
        Arrays.fill(constraints.fixed, -1);
        for (int i = 0; i < L; i++) Arrays.fill(constraints.bannedPos[i], false);
        Arrays.fill(constraints.minCount, 0);
        Arrays.fill(constraints.maxCount, -1); // -1 significa "sem teto ainda"
    }

    /**
     * Avalia um palpite textual (com ou sem acento).
     */
    public Feedback evaluate(String rawGuess) {
        // 1) Normaliza o palpite para A–Z (acentos/ç são ignorados)
        String guess = Dictionary.normalize(rawGuess);
        if (guess.length() != L()) {
            throw new IllegalArgumentException("Digite uma palavra de " + L() + " letras.");
        }

        int L = L();
        FeedbackColor[] colors = new FeedbackColor[L];

        // 2) Conta quantas vezes cada letra aparece na palavra secreta (para tratar repetidas)
        int[] remaining = new int[26];
        for (int i = 0; i < L; i++) {
            int id = Constraints.idx(secret.charAt(i));
            if (id >= 0) remaining[id]++;
        }

        // 3) Primeiro passe: marca GREEN onde houver match exato; reduz contagem disponível
        for (int i = 0; i < L; i++) {
            char g = guess.charAt(i);
            if (g == secret.charAt(i)) {
                colors[i] = FeedbackColor.GREEN;
                remaining[Constraints.idx(g)]--;
            }
        }

        // 4) Segundo passe: YELLOW se ainda houver ocorrências daquela letra; senão GRAY
        for (int i = 0; i < L; i++) {
            if (colors[i] != null) continue;      // já verde
            int id = Constraints.idx(guess.charAt(i));
            if (remaining[id] > 0) {
                colors[i] = FeedbackColor.YELLOW;
                remaining[id]--;
            } else {
                colors[i] = FeedbackColor.GRAY;
            }
        }

        // 5) Atualiza restrições segundo este feedback
        applyFeedbackToConstraints(guess, colors);

        // 6) Retorna o envelope com o palpite canônico e as cores por posição
        return new Feedback(guess, colors);
    }

    // ---------- Atualização das Restrições ----------
    /**
     * Converte um feedback em restrições:
     * - Verdes: fixam a letra na posição; todas as outras letras ficam banidas nessa posição.
     * - Amarelos/Cinzas: banem a letra naquela posição específica (a letra pode existir em outra posição).
     * - Mínimo por letra: é o máximo acumulado de (GREEN+YELLOW) vistos para a letra ao longo das jogadas.
     * - Máximo por letra: quando, numa jogada, há ocorrências cinzas "a mais" daquela letra,
     *   reduzimos o teto para o número de (GREEN+YELLOW) visto naquela jogada; acumulamos como o mínimo entre tetos.
     *
     * Observação: os limites (min/max) são acumulativos entre jogadas (max é o mínimo dos tetos observados;
     * min é o máximo das exigências observadas). Isso é essencial para tratar letras repetidas.
     */
    private void applyFeedbackToConstraints(String guess, FeedbackColor[] colors) {
        final int L = L();

        // --- (A) Restrições por posição ---
        for (int pos = 0; pos < L; pos++) {
            int id = Constraints.idx(guess.charAt(pos));
            if (colors[pos] == FeedbackColor.GREEN) {
                // fixa a letra nessa posição
                constraints.fixed[pos] = id;
            } else {
                // amarelo/cinza ⇒ a letra não pode ficar nessa posição específica
                // (se essa posição estiver fixa por jogada anterior, ignoramos este banimento local).
                if (constraints.fixed[pos] == -1) {
                    constraints.bannedPos[pos][id] = true;
                }
            }
        }

        // Para quaisquer posições fixas, banir todas as outras letras naquela posição
        for (int pos = 0; pos < L; pos++) {
            int keep = constraints.fixed[pos];
            if (keep != -1) {
                for (int id = 0; id < 26; id++) {
                    constraints.bannedPos[pos][id] = (id != keep);
                }
            }
        }

        // --- (B) Restrições globais por letra (mín./máx.) ---
        int[] gyInThisGuess = new int[26];  // quantas vezes a letra apareceu como GREEN ou YELLOW nesta jogada
        int[] totalInGuess  = new int[26];  // quantas vezes a letra foi chutada nesta jogada

        for (int i = 0; i < L; i++) {
            int id = Constraints.idx(guess.charAt(i));
            totalInGuess[id]++;
            if (colors[i] == FeedbackColor.GREEN || colors[i] == FeedbackColor.YELLOW) {
                gyInThisGuess[id]++;
            }
        }

        for (int id = 0; id < 26; id++) {
            // Exigência mínima acumulada: precisa ser pelo menos o maior #G/#Y já observado
            constraints.minCount[id] = Math.max(constraints.minCount[id], gyInThisGuess[id]);

            // Teto (máximo) sugerido por esta jogada:
            // Se nesta jogada a letra apareceu mais vezes do que o total de G/Y (ou seja, houve GRAY "em excesso"),
            // então esta jogada impõe max ≤ gyInThisGuess[id].
            if (totalInGuess[id] > gyInThisGuess[id]) {
                int impliedMax = gyInThisGuess[id]; // pode ser 0 (letra totalmente ausente ⇒ max=0)
                if (constraints.maxCount[id] == -1) {
                    constraints.maxCount[id] = impliedMax;
                } else {
                    constraints.maxCount[id] = Math.min(constraints.maxCount[id], impliedMax);
                }
            }

            // Coerência: se já temos um teto, ele não pode ficar abaixo do mínimo exigido
            if (constraints.maxCount[id] != -1) {
                constraints.maxCount[id] = Math.max(constraints.maxCount[id], constraints.minCount[id]);
            }
        }
    }
}