public class Ex2_int {
    public static void main(String args[]){
        long compar = 0;
        long aritOp = 0;

        int N = 500000;
        int vet1[] = new int[N];
        int vet2[] = new int[N];

        for(int i=0; i<N; i++){
            vet1[i] = -1;
        }

        for(int i=0; i<N; i++){
            compar++;               // Incremento da comparação do for
            vet2[i] = vet1[i];
            aritOp++;               // Incremento aritmético i = i +1
        }
        compar++;       // Incremento da comparação i = N
        System.out.println("Número de comparações.: " + compar);
        System.out.println("Número de operações aritméticas.: " + aritOp);
    }
}
