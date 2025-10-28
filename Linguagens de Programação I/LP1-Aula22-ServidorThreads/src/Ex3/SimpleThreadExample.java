package Ex3;

import java.util.Random;

public class SimpleThreadExample {
    // Geracao de um bilhao de numeros aletórios
    private static final long TOTAL_NUMEROS = 1_000_000_000L;

    public static void main(String[] args) {
        final int threads = 1; // criar apenas 1 outra thread
        System.out.println("Missão: Gerar um bilhao de números ateatórios");
        System.out.println("Numero de threads: " + threads);
        new SimpleThreadExample().run();

//Missão: Gerar um bilhao de números ateatórios
//Numero de threads: 1
//- Iniciando Thread Principal...
//- Encerrando o processamento...
//Misão cumprida em 19,66 segundos

    }

    public void run(){
        System.out.println("- Iniciando Thread Principal...");
        final double startTime = System.currentTimeMillis();
        Random rand = new Random();
        // eleva a 10a potencia cada numero para o processamento mais completo
        for (long i = 0; i < TOTAL_NUMEROS; i++) {
            Math.pow(rand.nextDouble(), 10);
        }
        final double totalSecs = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("- Encerrando o processamento...");
        System.out.printf("Misão cumprida em %.2f segundos\n", totalSecs);

    }
}
