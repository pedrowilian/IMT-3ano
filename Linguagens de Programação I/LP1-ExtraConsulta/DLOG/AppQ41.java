import java.io.*;
import java.nio.charset.StandardCharsets;
import saver.TextFileSaver;

// usamos a MESMA CheckSum, CryptoDummy e Impressora do exercício anterior
public class AppQ41 {
    public static void main(String[] args) {
        try {
            // 1) Monta os 24 caracteres (hard code)
            char[] dados24 = new char[] {
                'E','n','g','e','n','h','a','r','i','a','_',
                'd','a','_','C','o','m','p','u','t','a','c','a','o'
            };

            // 2) Aloca vetor de 25 e calcula o checksum (Soma + Complemento 2) nos 24 primeiros
            char[] msg25 = new char[25];
            System.arraycopy(dados24, 0, msg25, 0, 24);
            char ck = CheckSum.calcularComplemento2(dados24); // REUSO do seu método
            msg25[24] = ck; // checksum no índice 24

            // Validação rápida (REUSO do seu método)
            String textoComCk = new String(msg25);
            boolean ok = CheckSum.checkSum(textoComCk);
            System.out.println("Checksum calculado = " + (int)(ck & 0xFF) + " | válido? " + ok);

            // 3) Converte para bytes 1:1 e cifra com a DUMMY (REUSO)
            byte[] plain = new String(msg25).getBytes(StandardCharsets.ISO_8859_1);

            CryptoDummy dummy = new CryptoDummy();
            File fChave = new File("chave.dummy");                 // mesmo nome de antes, pode manter
            File fCifrado = new File("mensagem_cifrada.bin");      // mesmo nome de antes, pode manter

            // DUMPS do claro ANTES de cifrar
            Impressora prn = new Impressora();
            System.out.println("\n=== DADOS (24 chars + checksum) ===");
            System.out.println("Como String: " + textoComCk);
            System.out.println("Hex:\n" + prn.hexBytesToString(plain));

            dummy.geraChave(fChave);
            dummy.geraCifra(plain, fChave);
            byte[] cipher = dummy.getTextoCifrado();

            System.out.println("=== CIFRADO (DUMMY) ===");
            System.out.println("Hex:\n" + prn.hexBytesToString(cipher));

            // 4) Salva arquivos de trabalho (chave e mensagem cifrada)
            try (FileOutputStream fos = new FileOutputStream(fCifrado)) {
                fos.write(cipher);
            }

            // (opcional) relatório .txt, como você já fez antes
            TextFileSaver rep = new TextFileSaver();
            rep.openFilWrite("relatorio_q4.txt");
            rep.saveText("Mensagem (25, com checksum): " + textoComCk + "\n\n");
            rep.saveText("PLAIN HEX:\n" + prn.hexBytesToString(plain) + "\n");
            rep.saveText("CIPHER HEX:\n" + prn.hexBytesToString(cipher) + "\n");
            rep.closeFile();

            System.out.println("\nArquivos gerados:");
            System.out.println(" - " + fChave.getAbsolutePath());
            System.out.println(" - " + fCifrado.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
