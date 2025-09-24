class Ex4_int {
    public static void main(String[] args) throws Exception {
        int N = 100000;
        int M = 100000;
        int Mat[][] = new int[N][M];
        int Mat_T[][] = new int[M][N];
        int total;
        int check=0;
        long compar = 0;
        long aritOp = 0;
        
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                Mat[i][j] = 0;

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                Mat_T[j][i]=Mat[i][j];

        total = N*M;
        aritOp++;         //incremento operação N*M
        for(int i=0; i<M; i++){
            aritOp++;                //incremento operação i = i+1
            compar++;                //incremento comparação i < M
            for(int j=0; j<N; j++){
                aritOp++;            //incremento operação j = j+1
                compar++;            //incremento comparação j < N
                if(Mat[i][j]==Mat_T[i][j]){
                    compar++;        //incremento comparação Mat=MatT
                    check++;
                }
            }
            compar++;                //incremento comparação j = N
        }
        compar++;                    //incremento comparação i = M                   
        if(check == total){
            compar++;                //incremento comparação check = total
            System.out.println("A matriz é simétrica");
        }
        else
            System.out.println("A matriz não é simétrica");

        System.out.println("Número de comparações.: " + compar);
        System.out.println("Número de operações aritméticas.: " + aritOp);
    }
}
