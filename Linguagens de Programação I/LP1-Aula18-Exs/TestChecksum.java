// Classe de teste unitarios para Checksum
public class TestChecksum {
    // Teste do metodo calcularChecksum
    public static String TesteCalcularChecksum(char[] dados, char esperado){
        int resultado = Checksum.calcularChecksum(dados);
        int resposta = (int) esperado;
        if (resultado == resposta){
            return "Teste passou: " + resultado + " == " + resposta;
        } else {
            return "Teste falhou: " + resultado + " != " + resposta;
        }
    }
    // Teste do metodo calcularChecksumDoArquivoTexto
    public static String TesteCalcularChecksumDoArquivoTexto(String nomeArquivo, char esperado){
        int resultado = Checksum.calcularChecksumDoArquivoTexto(nomeArquivo);
        int resposta = (int) esperado;
        if (resultado == resposta){
            return "Teste passou: " + resultado + " == " + resposta;
        } else {
            return "Teste falhou: " + resultado + " != " + resposta;
        }
    }
    // Teste do método calcularCRC
    public static String TesteCalcularCRC(char[] dados, int esperado) {
        int resultado = Checksum.calcularCRC(dados);
        char[] dadosComCRC = new char[dados.length + 1];
        System.arraycopy(dados, 0, dadosComCRC, 0, dados.length);
        dadosComCRC[dados.length] = (char) resultado;
        int crcVerificacao = Checksum.calcularCRC(dadosComCRC);
        if (crcVerificacao == 0)
            return "Teste passou (CRC)";
        else
            return "Teste falhou (CRC)";
    }

    // Teste do método calcularCRCDoArquivoTexto
    public static String TesteCalcularCRCDoArquivoTexto(String nomeArquivo) {
        // Lê o conteúdo do arquivo
        ReadTextFile application = new ReadTextFile();
        application.openFile(nomeArquivo);
        String txt = application.readFile();
        application.closeFile();

        // Calcula o CRC do conteúdo
        int resultado = Checksum.calcularCRC(txt.toCharArray());

        // Monta mensagem original + CRC
        char[] dadosComCRC = new char[txt.length() + 1];
        System.arraycopy(txt.toCharArray(), 0, dadosComCRC, 0, txt.length());
        dadosComCRC[txt.length()] = (char) resultado;

        // Verifica
        int crcVerificacao = Checksum.calcularCRC(dadosComCRC);

        if (crcVerificacao == 0)
            return "Teste passou (CRC Arquivo)";
        else
            return "Teste falhou (CRC Arquivo)";
    }
}
