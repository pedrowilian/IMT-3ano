class Ex1_tempo {
    public static void main(String[] args){
        int N = 500000;
        int vet[] = new int[N];
        long startTime = System.nanoTime(); // Captura inicial do relogio
        for (int i = 0; i<N;i++){
            vet[i] = 0;
        }

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Tempo de execucao em Nanosegundos: "+ timeElapsed);
        System.out.println("Tempo de execucao em Microsegundos: "+ timeElapsed/1000);
        System.out.println("Tempo de execucao em Milisegundos: "+ timeElapsed/1000/1000);
        System.out.println("Tempo de execucao em Segundos: " + timeElapsed/1000/1000/1000);
    }
}
