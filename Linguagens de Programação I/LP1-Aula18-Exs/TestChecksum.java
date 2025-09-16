// Classe de teste unitarios para Checksum
public class TestChecksum {
    // Teste do metodo calcularChecksum
    public static String TesteCalcularChecksum(char[] dados, String esperado){
        String resultado = Checksum.calcularChecksum(dados);
        if (resultado.equals(esperado)){
            return "Teste passou: " + resultado + " == " + esperado;
        } else {
            return "Teste falhou: " + resultado + " != " + esperado;
        }
    }
}
