public class Checksum {
    // Construtor da classe Checksum
    public Checksum() {
    }

    // O checksum recebe vetor de caracteres e retorna baseado na soma e complemento de 2 em binario
    public static int calcularChecksum(char[] dados) {
        int soma = 0;
        for (char c : dados)
            soma += (int) c;
        int soma8bits = soma & 0xFF; // Limita a soma a 8 bits
        int complemento = (~soma8bits + 1) & 0xFF; // Complemento de 2 em 8 bits
        return complemento;
    }

    // Calcular o Checksum de um arquivo de texto
    public static int calcularChecksumDoArquivoTexto(String nomeArquivo) {
        ReadTextFile application = new ReadTextFile();
        application.openFile(nomeArquivo);
        String txt = application.readFile();
        application.closeFile();
        return calcularChecksum(txt.toCharArray());
    }
}