public class Ex3_tempo {
    public static void main(String args[]) throws InterruptedException{
        int N = 500000;
        int M = 1000;
        double mat[][] = new double[N][M];

        long startTime = System.nanoTime();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                mat[i][j] = -1;
            }
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tempo de execução em Nanosegundos: " + elapsedTime);
        System.out.println("Tempo de execução em Microsegundos: " + elapsedTime / 1000);
        System.out.println("Tempo de execução em Milisegundos: " + elapsedTime / 1000 / 1000);
        System.out.println("Tempo de execucao em Segundos: " + elapsedTime/1000/1000/1000);
    }
}