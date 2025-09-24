class Ex4_tempo {
    public static void main(String[] args) throws Exception {
        int N = 50000;
        int M = 50000;
        int Mat[][] = new int[N][M];
        int Mat_T[][] = new int[M][N];
        int total;
        int check=0;

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                Mat[i][j] = 0;

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                Mat_T[j][i]=Mat[i][j];

        long startTime = System.nanoTime();
        total = N*M;
        for(int i=0; i<M; i++)
            for(int j=0; j<N; j++)
                if(Mat[i][j]==Mat_T[i][j])
                    check++;
        if(check == total)
            System.out.println("A matriz é simétrica");
        else
            System.out.println("A matriz não é simétrica");
            
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Tempo em execução em Nanosegundos: " + timeElapsed);
        System.out.println("Tempo em execução em Microsegundos: " + timeElapsed / 1000);
        System.out.println("Tempo em execução em Milisegundos: " + timeElapsed / 1000 / 1000);
        System.out.println("Tempo de execucao em Segundos: " + timeElapsed/1000/1000/1000);
    }
}
