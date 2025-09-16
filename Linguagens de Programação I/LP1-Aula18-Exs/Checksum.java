public class Checksum {
    // Construtor da classe Checksum
    public Checksum() {
    }
    

    // calcularChecksum() recebe vetor de caracteres e retorna baseado na soma e complemento de 2
    public static String calcularChecksum(char[] dados){
        int soma = 0;
        for (char c : dados)
            soma += (int) c;
        int complemento = ~soma + 1;
        return Integer.toHexString(complemento);
    
    }

}
