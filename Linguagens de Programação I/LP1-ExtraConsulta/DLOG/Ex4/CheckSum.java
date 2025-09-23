// package Ex4;
import java.util.ArrayList;
import reader.TxtReader;
import saver.TextFileSaver;

public class CheckSum {

    // Calcula o CRC-8 usando o polinômio 0x07
    public static byte calcularCRC8(byte[] dados) {
        byte crc = 0x00;
        for (byte b : dados) {
            crc ^= b;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x80) != 0) {
                    crc = (byte) ((crc << 1) ^ 0x07);
                } 
                else {
                    crc <<= 1;
                }
            }
        }
        return crc;
    }

    // Calcula o CRC-8 de uma string
    public static byte calcularCRC8(String dados) {
        return calcularCRC8(dados.getBytes());
    }

    // Calcula o complemento de 2 de um arquivo e salva em outro arquivo
    public static void calcularComplemento2Arquivo(String src, String target) {
        ArrayList<String> lines = TxtReader.readFile(src);
        ArrayList<String> linesComplemento2 = new ArrayList<>();
        for (String line : lines) {
            char[] vetor = line.toCharArray();
            char complemento2 = calcularComplemento2(vetor);
            linesComplemento2.add( line + complemento2);
        }
        TextFileSaver textFileSaver = new TextFileSaver();
        textFileSaver.openFilWrite(target);
        for (String line : linesComplemento2) {
            textFileSaver.saveText(line + "\n");
        }
        textFileSaver.closeFile();
    }

    public static char calcularComplemento2(char[] vetor) {
        // Transforma chars em inteiros
        int[] vetorInt = new int[vetor.length];
        for (int i = 0; i < vetor.length; i++) {
            vetorInt[i] = (int) vetor[i];
        }

        // Soma dos inteiros 
        int sum = 0;
        for (int i = 0; i < vetorInt.length; i++) {
            sum += vetorInt[i];
        }

        // Converte soma para binario
        String sumBinary = Integer.toBinaryString(sum);

        // Verifica tamanho do binario
        int sumBinaryLength = sumBinary.length();

        // Eliminação do bit excedente (bits > 8 -> elimina o da direita)
        if (sumBinaryLength > 8) {
            // Pega os 8 bits menos significativos
            sumBinary = sumBinary.substring(sumBinaryLength - 8, sumBinaryLength);
            
            // Atualiza o tamanho 
            sumBinaryLength = sumBinary.length();
        }

        // Complemento de 1 (inverte os bits)
        StringBuilder invertido = new StringBuilder();
        for (int i = 0; i < sumBinaryLength; i++) {
            if (sumBinary.charAt(i) == '0') {
                invertido.append("1");
            } 
            else {
                invertido.append("0");
            }
        }

        // Complemento de 2 (soma 1 ao complemento de 1)
        String complemento2String   = invertido.toString(); // String com complemento de 1
        int    complemento2Int      = Integer.parseInt(complemento2String, 2);  // Inteiro na base 2 (complemento de 1)
        int    complemento2IntPlus1 = complemento2Int + 1;  // Inteiro na base 2 (complemento de 2)

        // Converte o complemento de 2 para binario
        String complemento2IntPlus1Binary = Integer.toBinaryString(complemento2IntPlus1);

        // Verifica o tamanho do binario do complemento de 2
        int complemento2IntPlus1BinaryLength = complemento2IntPlus1Binary.length();

        // Eliminação do bit excedente (bits > 8 -> elimina o da direita)
        if (complemento2IntPlus1BinaryLength < 8) {
            // Adiciona 0's a esquerda para completar 8 bits
            int preenchimento = 8 - complemento2IntPlus1BinaryLength;
            
            // Concatena os 0's com o binario do complemento de 2
            StringBuilder preenchimentoBuilder = new StringBuilder();

            // Criação dos 0's
            for (int i = 0; i < preenchimento; i++) {
                preenchimentoBuilder.append("0");
            }

            // Concatenação dos 0's com o binario do complemento de 2
            preenchimentoBuilder.append(complemento2IntPlus1Binary);

            // Atualiza o binario do complemento de 2
            complemento2IntPlus1Binary = preenchimentoBuilder.toString();
        }
        // Retorna o char do complemento de 2
        return (char) Integer.parseInt(complemento2IntPlus1Binary, 2);
    }

    // Verifica se o complemento de 2 é válido (soma dos caracteres + complemento de 2 = 0)
    public static boolean checkSum(String complemento2) {
        // Transforma a String em um vetor de chars
        char[] vetor = complemento2.toCharArray();

        // Transforma chars em inteiros
        int[] vetorInt = new int[vetor.length];
        for (int i = 0; i < vetor.length; i++) {
            vetorInt[i] = (int) vetor[i];
        }

        // Soma dos inteiros
        int sum = 0;
        for (int i = 0; i < vetorInt.length; i++) {
            sum += vetorInt[i];
        }

        // Converte soma para binario
        String sumBinary = Integer.toBinaryString(sum);

        // Verifica tamanho do binario
        int sumBinaryLength = sumBinary.length();

        // Eliminação do bit excedente (bits > 8 -> elimina o da direita)
        if (sumBinaryLength > 8) {
            sumBinary = sumBinary.substring(sumBinaryLength - 8, sumBinaryLength);
            sumBinaryLength = sumBinary.length();
        }

        // Verifica se o binario é igual a 0
        if (Integer.parseInt(sumBinary, 2) == 0) {
            return  true;
        }
        else {
            return false;
        }
    }
}
