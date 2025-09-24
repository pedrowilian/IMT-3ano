public class Ex2 {
    public static void main(String[] args) {
        double[] vet = {1, 2, 3, 4, 5}; 
        double resultado = media(vet);
        System.out.println(resultado);
    }
    public static double media(double[] vet) {
        double soma = 0;
        for (int i = 0; i < vet.length; i++)
            soma += vet[i];
        return soma/vet.length;
    } 
}
