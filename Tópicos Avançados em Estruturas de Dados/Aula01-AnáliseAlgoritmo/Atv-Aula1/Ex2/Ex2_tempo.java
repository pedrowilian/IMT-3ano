class Ex2_tempo {
    public static void main(String args[]) throws InterruptedException {
        int N = 500000;
        int vet1[] = new int[N];
        int vet2[] = new int[N];

        for(int i=0; i<N; i++){
            vet1[i] = -1;
        }

        long startTime = System.nanoTime();

        for(int i=0; i<N; i++)
        {
            vet2[i] = vet1[i];
        }
        long endTime = System.nanoTime();                
        long elapsedTime = endTime - startTime;         
        System.out.println("Tempo de execução em Nanosegundos: " + elapsedTime);
        System.out.println("Tempo de execução em Microsegundos: " + elapsedTime / 1000);
        System.out.println("Tempo de execução em Milisegundos: " + elapsedTime / 1000 / 1000);
        System.out.println("Tempo de execucao em Segundos: " + elapsedTime/1000/1000/1000);
    }
}
