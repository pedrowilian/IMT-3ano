public class Ex1 {
    public static void main(String[] args) {
        int n = 5;
        int resultado = fatorial(n);
        System.out.println(resultado);
    }
    public static int fatorial(int n) {
        int total = 1;
        for (int i = 1; i <= n; i++)
            total *= i;
        return total;
    }
}


