// package Ex4;
import java.io.*;          
import java.nio.charset.StandardCharsets;
import saver.TextFileSaver;

public class AppQ4 {
    public static void main(String[] args) {
        try {
            // 1) Monta os 8 caracteres
            char[] dados8 = new char[] {'#','V','a','i','I','M','T','!'};
            // char[] dados24 = new char[]{
            // 'E','n','g','e','n','h','a','r','i','a','_',
            // 'd','a','_','C','o','m','p','u','t','a','c','a','o'
            // }; // 24

            // 2) Aloca vetor de 9 e calcula o checksum (usando seu CheckSum)
            char[] msg9 = new char[9];
            System.arraycopy(dados8, 0, msg9, 0, 8);
            char ck = CheckSum.calcularComplemento2(dados8); // reutilizando seu método
            msg9[8] = ck;
            // char ck = CheckSum.calcularComplemento2(dados24);
            // msg25[24] = ck;



            // (opcional) validação rápida
            String textoComCk = new String(msg9);
            boolean ok = CheckSum.checkSum(textoComCk);
            System.out.println("Checksum calculado = " + (int)(ck & 0xFF) + " | válido? " + ok);

            // 3) Converte para bytes (1:1) e cifra com a DUMMY
            byte[] plain = new String(msg9).getBytes(StandardCharsets.ISO_8859_1);

            CryptoDummy dummy = new CryptoDummy();
            File fChave = new File("chave.dummy");
            File fCifrado = new File("mensagem_cifrada.bin");

            dummy.geraChave(fChave);
            dummy.geraCifra(plain, fChave);
            byte[] cipher = dummy.getTextoCifrado();

            // 4) Exibe resultado em tela
            Impressora prn = new Impressora();
            System.out.println("\n=== DADOS (9 chars: 8 + checksum) ===");
            System.out.println("Como String: " + textoComCk);
            System.out.println("Hex:\n" + prn.hexBytesToString(plain));

            System.out.println("=== CIFRADO (DUMMY) ===");
            System.out.println("Hex:\n" + prn.hexBytesToString(cipher));

            // 5) Salva arquivos de trabalho exigidos pela prova
            try (FileOutputStream fos = new FileOutputStream(fCifrado)) {
                fos.write(cipher);
            }

            // (opcional) gerar um relatório .txt com as impressões
            TextFileSaver rep = new TextFileSaver();
            rep.openFilWrite("relatorio_q4.txt");
            rep.saveText("Mensagem (9): " + textoComCk + "\n\n");
            rep.saveText("PLAIN HEX:\n" + prn.hexBytesToString(plain) + "\n");
            rep.saveText("CIPHER HEX:\n" + prn.hexBytesToString(cipher) + "\n");
            rep.closeFile();

            // depois de obter 'cipher'
            dummy.geraDecifra(cipher, fChave);
            byte[] dec = dummy.getTextoDecifrado();

            // 1) deve imprimir 9, 9, true
            System.out.println(dec.length + ", " + plain.length + ", " + java.util.Arrays.equals(dec, plain));

            // 2) checksum do texto decifrado deve ser zero ao somar tudo (dados+ck)
            System.out.println("re-check: " + CheckSum.checkSum(new String(dec, java.nio.charset.StandardCharsets.ISO_8859_1)));


            System.out.println("\nArquivos gerados:");
            System.out.println(" - " + fChave.getAbsolutePath());
            System.out.println(" - " + fCifrado.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
