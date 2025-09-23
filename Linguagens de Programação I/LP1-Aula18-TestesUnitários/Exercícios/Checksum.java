public class Checksum {
    // Construtor da classe Checksum
    public Checksum() {
    }

    // calcularChecksum() recebe vetor de caracteres e retorna baseado na soma e complemento de 2 em binario
    public static int calcularChecksum(char[] dados) {
        int soma = 0;
        for (char c : dados)
            soma += (int) c;
        System.out.println("Soma dos valores ASCII: " + soma);
        int soma8bits = soma & 0xFF; // Limita a soma a 8 bits
        String binario = String.format("%8s", Integer.toBinaryString(soma8bits)).replace(' ', '0');
        System.out.println("Soma em binário (8 bits): " + binario);
        int complemento = (~soma8bits + 1) & 0xFF; // Complemento de 2 em 8 bits
        System.out.println("Complemento de 2 em binário (8 bits): " + 
                           String.format("%8s", Integer.toBinaryString(complemento)).replace(' ', '0'));
        return complemento;
    }

    // Método calcularChecksumDoArquivoTexto()
    public static int calcularChecksumDoArquivoTexto(String nomeArquivo) {
        ReadTextFile application = new ReadTextFile();
        application.openFile(nomeArquivo);
        String txt = application.readFile();
        application.closeFile();
        return calcularChecksum(txt.toCharArray());
    }

    public static int calcularCRC(char[] dados) {
        int crc = 0x00; // valor inicial
        int polinomio = 0x07; // CRC-8 padrão

        for (char c : dados) {
            crc ^= (int) c; // Aplica o XOR com o byte da mensagem

            for (int i = 0; i < 8; i++) {
                if ((crc & 0x80) != 0) { // Se o bit mais significativo for 1
                    crc = (crc << 1) ^ polinomio; // Faz o shift e aplica XOR com o polinômio
                } else {
                    crc <<= 1; // Apenas shift
                }
                crc &= 0xFF; // Garante que fique em 8 bits
            }
        }

        System.out.println("CRC-8 calculado: " + String.format("%8s", Integer.toBinaryString(crc)).replace(' ', '0'));
        return crc;
    }

    // Método calcularCRCDoArquivoTexto()
    public static int calcularCRCDoArquivoTexto(String nomeArquivo) {
        ReadTextFile application = new ReadTextFile();
        application.openFile(nomeArquivo);
        String txt = application.readFile();
        application.closeFile();
        return calcularCRC(txt.toCharArray());
    }



}
