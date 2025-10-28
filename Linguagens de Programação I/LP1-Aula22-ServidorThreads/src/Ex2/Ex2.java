package Ex2;

interface Operacao{
    int executar(int a, int b);
}

public class Ex2 {
    public static void main(String[] args) {
        // Exemplo 4
        Operacao adicao = new Operacao(){
            @Override
            public int executar(int a, int b) {
                return a + b;
            }
        };

        int resultado = adicao.executar(5, 3);
        System.out.println("Resultado: " + resultado);

        // Exemplo 5
        Operacao adicao2 = (a, b) -> a + b;
        resultado = adicao2.executar(5, 3);
        System.out.println("Resultado: " + resultado);

        /*
            >> Terminal
            Resultado: 8
            Resultado: 8
        */
    }
}

