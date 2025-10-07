import fileHandler.TxtReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckSumTest {
    CheckSum checkSum = new CheckSum();

    char[] vetor = {'C', 'a', 's', 'a', '1'};
    char auxExpected = 'W';
    String s1Expected = "Casa1W";
    String s2Expected = "Casa2V";
    long esperadoCRC = 560253239;


    @Test
    @DisplayName("Testa cálculo do complemento 2")
    public void testaCalcularComplemento2() {
        char aux = checkSum.calcularComplemento2(vetor);
        assertEquals(auxExpected, aux);
    }

    @Test
    @DisplayName("Testa cálculo do checksum")
    public void testaCheckSum() {
        boolean aux = checkSum.checkSum(s1Expected);
        assertTrue(aux);
    }

    @Test
    @DisplayName("Testa cálculo do complemento 2 de arquivo")
    public void testaCalcularComplemento2Arquivo() {
        checkSum.calcularComplemento2Arquivo("src/ex4.txt", "src/ex4_resp.txt");
        String s1 = TxtReader.readFile("src/ex4_resp.txt").get(0);
        String s2 = TxtReader.readFile("src/ex4_resp.txt").get(1);


        assertEquals(s1, s1Expected);
        assertEquals(s2, s2Expected);

    }

    @Test
    @DisplayName("Testa cálculo do CRC")
    public void testCalcularCRC(){
        CheckSum checksum = new CheckSum();
        //Dados de entrada de exemplo
        char[] dados = "Casa1".toCharArray();
        //O valor em CRC32 (em decimal) de referência para "Hello World"

        //Calcula o CRC com a sua implementação
        long atualCRC = checksum.calcularCRC(dados);

        //Compara o resultado com o valor esperado
        assertEquals(esperadoCRC, atualCRC);
    }


}
