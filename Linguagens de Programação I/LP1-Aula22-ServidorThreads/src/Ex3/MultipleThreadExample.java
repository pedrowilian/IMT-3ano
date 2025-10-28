//Exemplo 9

/*

        Missão: Gerar um bilhao de números aleatórios usando Threads!
        -> Thread inicial: main...
        -> Criando +1 threads...
        ----> Thread criada (Thread-0)
        ----> Iniciando Thread (Thread-0)...
        -> Thread finalizada (main) em 0,00 segundos
        ----> Encerrando Thread(Thread-0)!
        ----> Missao da Thread (Thread-0) cumprida em 19,65 segundos

*/


// Exemplo 10

/*

        Missão: Gerar um bilhao de números aleatórios usando Threads!
        -> Thread inicial: main...
        -> Criando +2 threads...
        ----> Thread criada (Thread-0)
        ----> Thread criada (Thread-1)
        ----> Iniciando Thread (Thread-0)...
        -> Thread finalizada (main) em 0,00 segundos
        ----> Iniciando Thread (Thread-1)...
        ----> Encerrando Thread(Thread-1)!
        ----> Missao da Thread (Thread-1) cumprida em 10,11 segundos
        ----> Encerrando Thread(Thread-0)!
        ----> Missao da Thread (Thread-0) cumprida em 10,11 segundos

*/


// Exemplo 11

/*

        Missão: Gerar um bilhao de números aleatórios usando Threads!
        -> Thread inicial: main...
        -> Criando +3 threads...
        ----> Thread criada (Thread-0)
        ----> Thread criada (Thread-1)
        ----> Iniciando Thread (Thread-0)...
        ----> Thread criada (Thread-2)
        ----> Iniciando Thread (Thread-1)...
        -> Thread finalizada (main) em 0,00 segundos
        ----> Iniciando Thread (Thread-2)...
        ----> Encerrando Thread(Thread-0)!
        ----> Missao da Thread (Thread-0) cumprida em 7,46 segundos
        ----> Encerrando Thread(Thread-2)!
        ----> Missao da Thread (Thread-2) cumprida em 7,46 segundos
        ----> Encerrando Thread(Thread-1)!
        ----> Missao da Thread (Thread-1) cumprida em 7,47 segundos

*/

package Ex3;

import java.util.Random;

public class MultipleThreadExample extends Thread{
    // Geracao de um bilhao de numeros aleatórios
    private static final long TOTAL_NUMEROS = 1_000_000_000L;
    private final long numerosAGerar;

    public MultipleThreadExample(long numerosAGerar){
        this.numerosAGerar = numerosAGerar;
        System.out.printf("----> Thread criada (%s)\n", this.getName());
    }

    public static void main(String[] args) {
        final int threads = args.length == 0 ? 1 : Integer.parseInt(args[0]);
        final long numeroPorThread = TOTAL_NUMEROS / threads;

        System.out.printf("\nMissão: Gerar um bilhao de números aleatórios usando Threads!\n");

        System.out.printf("-> Thread inicial: %s...\n", Thread.currentThread().getName());
        System.out.printf("-> Criando +%d threads...\n", threads);

        // Le o tempo sistema no inicial

        final double startTime = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            new MultipleThreadExample(numeroPorThread).start();
        }

        // Tempo de processamento em segundos

        final double totalSecs = (System.currentTimeMillis() - startTime) / 1000;

        System.out.printf("-> Thread finalizada (%s) em %.2f segundos\n", Thread.currentThread().getName(), totalSecs);
    }

    @Override
    public void run(){
        System.out.printf("----> Iniciando Thread (%s)...\n", Thread.currentThread().getName());
        final double startTime = System.currentTimeMillis();
        Random rand = new Random();
        // eleva a 10a potencia cada numero para o processamento mais completo

        for (long i = 0; i < numerosAGerar; i++) {
            Math.pow(rand.nextDouble(), 10);
        }

        final double totalSecs = (System.currentTimeMillis() - startTime) / 1000;
        System.out.printf("----> Encerrando Thread(%s)!\n", Thread.currentThread().getName());
        System.out.printf("----> Missao da Thread (%s) cumprida em %.2f segundos\n", Thread.currentThread().getName(), totalSecs);

    }
}
