package wordgraphle.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.*;

public final class Dictionary {
    private final int L;
    private final List<String> words;                   // canônicas (A–Z, sem acento)
    private final LinkedHashMap<String,String> display; // canônica -> exibição (com acento)

    private Dictionary(int L, LinkedHashMap<String,String> display) {
        this.L = L;
        this.display = display;
        this.words = new ArrayList<>(display.keySet());
    }

    public List<String> words() { return words; }
    public int L() { return L; }
    public String displayOf(String canonical) { return display.getOrDefault(canonical, canonical); }

    /** Normaliza para A–Z: remove acentos, 'ç'->'c', tira não-letras e uppercase. */
    public static String normalize(String w) {
        if (w == null) return "";
        String s = Normalizer.normalize(w, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replace('ç','c').replace('Ç','C')
                .replaceAll("[^A-Za-z]", "")
                .toUpperCase(Locale.ROOT);
        return s;
    }

    /** Carrega de arquivo texto (UTF-8), uma palavra por linha. */
    public static Dictionary loadFromFile(File f, int L) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
            return loadFromReader(br, L);
        }
    }

    /** Carrega de um recurso do classpath (p.ex. "/palavras.txt" ou "/wordgraphle/palavras.txt"). */
    public static Dictionary loadFromResource(String resourcePath, int L) throws IOException {
        InputStream in = Dictionary.class.getResourceAsStream(resourcePath);
        if (in == null) throw new FileNotFoundException("Recurso não encontrado: " + resourcePath);
        return loadFromInputStream(in, L);
    }

    /** Carrega de um InputStream (UTF-8). Útil para resources do classpath. */
    public static Dictionary loadFromInputStream(InputStream in, int L) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return loadFromReader(br, L);
        }
    }

    // ------ implementação comum de parsing ------
    private static Dictionary loadFromReader(BufferedReader br, int L) throws IOException {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        String line;
        while ((line = br.readLine()) != null) {
            String raw = line.trim();
            if (raw.isEmpty()) continue;
            String canon = normalize(raw);
            if (canon.length() == L) map.putIfAbsent(canon, raw);
        }
        if (map.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma palavra válida de L="+L+" após normalização.");
        }
        return new Dictionary(L, map);
    }
}