class Ex3_int {
    public static void main(String args[]) throws InterruptedException{
        long compar = 0;
        long aritOp = 0;

        int N = 500000;
        int M = 1000;
        double mat[][] = new double[N][M];

        for(int i=0; i<N; i++){
            compar++;                       //incremento comparação i < N
            aritOp++;                       //incremento operação i = i+1
            for(int j=0; j<M; j++){
                compar++;                   //incremento comparação j < M
                aritOp++;                    //incremento operação j = j+1
                mat[i][j] = -1;
            }
            compar++;                       //incremento comparação j = M
        }
        compar++;                           //incremento comparação i = N

        System.out.println("Número de comparações.: " + compar);
        System.out.println("Número de operações aritméticas.: " + aritOp);
    }
}
