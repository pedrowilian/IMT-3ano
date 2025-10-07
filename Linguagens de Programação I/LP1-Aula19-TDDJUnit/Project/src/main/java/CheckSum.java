import fileHandler.TextFileSaver;
import fileHandler.TxtReader;

import java.util.ArrayList;
import java.util.zip.CRC32;

public class CheckSum {
    public char calcularComplemento2(char[] vetor)
    {
        int[] vetorInt = new int[vetor.length];
        for (int i = 0; i < vetor.length; i++) {
            vetorInt[i] = (int) vetor[i];
        }

        int sum = 0;
        for (int i = 0; i < vetorInt.length; i++) {
            sum += vetorInt[i];
        }

        String sumBinary = Integer.toBinaryString(sum);

        int sumBinaryLength = sumBinary.length();

        if (sumBinaryLength > 8) {
            sumBinary = sumBinary.substring(sumBinaryLength - 8, sumBinaryLength);
            sumBinaryLength = sumBinary.length();
        }

        StringBuilder invertido = new StringBuilder();
        for (int i = 0; i < sumBinaryLength; i++) {
            if (sumBinary.charAt(i) == '0') {
                invertido.append("1");
            } else {
                invertido.append("0");
            }
        }

        String complemento2String = invertido.toString();
        int complemento2Int = Integer.parseInt(complemento2String, 2);
        int complemento2IntPlus1 = complemento2Int + 1;

        String complemento2IntPlus1Binary = Integer.toBinaryString(complemento2IntPlus1);

        int complemento2IntPlus1BinaryLength = complemento2IntPlus1Binary.length();

        if (complemento2IntPlus1BinaryLength < 8) {
            int padding = 8 - complemento2IntPlus1BinaryLength;
            StringBuilder paddingBuilder = new StringBuilder();
            for (int i = 0; i < padding; i++) {
                paddingBuilder.append("0");
            }
            paddingBuilder.append(complemento2IntPlus1Binary);
            complemento2IntPlus1Binary = paddingBuilder.toString();
        }

        return (char) Integer.parseInt(complemento2IntPlus1Binary, 2);
    }

    public boolean checkSum(String complemento2){

        char[] vetor = complemento2.toCharArray();

        int[] vetorInt = new int[vetor.length];
        for (int i = 0; i < vetor.length; i++) {
            vetorInt[i] = (int) vetor[i];
        }

        int sum = 0;
        for (int i = 0; i < vetorInt.length; i++) {
            sum += vetorInt[i];
        }

        String sumBinary = Integer.toBinaryString(sum);

        int sumBinaryLength = sumBinary.length();

        if (sumBinaryLength > 8) {
            sumBinary = sumBinary.substring(sumBinaryLength - 8, sumBinaryLength);
            sumBinaryLength = sumBinary.length();
        }

        if (Integer.parseInt(sumBinary, 2) == 0){
            return  true;
        }
        else{
            return false;
        }
    }

    public void calcularComplemento2Arquivo(String src, String target){
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

    public long calcularCRC(char[] dados){
        CRC32 crc = new CRC32();
        String str = new String(dados);
        byte[] bytes = str.getBytes();
        crc.update(bytes, 0, bytes.length);
        long valorCRC = crc.getValue();
        //System.out.println(valorCRC);
        return valorCRC;
    }


}
