public class Main {
    public static void main(String[] args) {
        // ================= TESTES UNITÁRIOS =================

        // Teste de Checksum com vetor
        char[] dados1 = {'C', 'a', 's', 'a', '1'};
        char esperadoChecksum = 'W'; // valor esperado (ajuste se necessário)
        System.out.println(TestChecksum.TesteCalcularChecksum(dados1, esperadoChecksum));

        // Teste de Checksum com arquivo
        char esperadoChecksumArquivo = 'W'; // substitua pelo valor correto esperado do arquivo
        String resultadoChecksumArquivo = TestChecksum.TesteCalcularChecksumDoArquivoTexto("teste.txt", esperadoChecksumArquivo);
        System.out.println(resultadoChecksumArquivo);

        // Teste de CRC com vetor
        char[] dados2 = {'C', 'a', 's', 'a', '1'};
        int esperadoCRC = 0x8F; // valor esperado (exemplo, precisa rodar uma vez para descobrir)
        System.out.println(TestChecksum.TesteCalcularCRC(dados2, esperadoCRC));

        // Teste de CRC com arquivo
        String resultadoCRCArquivo = TestChecksum.TesteCalcularCRCDoArquivoTexto("teste.txt");
        System.out.println(resultadoCRCArquivo);

        // ================= SAÍDA EM ARQUIVO =================
        CreateTextFile output = new CreateTextFile();
        output.openFile("output.txt");
        output.addText("Resultados dos testes:\n");
        output.addText(resultadoChecksumArquivo + "\n");
        output.addText(resultadoCRCArquivo + "\n");
        output.closeFile();
    }
}
