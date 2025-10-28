package Ex1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface Operacao {
    int executar(int a, int b);
}

interface Comparador<T> {
    int compare(T s1, T s2);
}

public class Ex1 {
    public static void main(String[] args) {
        // Exemplo 1
        Operacao soma = (a, b) -> a + b;
        int resultado = soma.executar(5,3);
        System.out.println("Resultado: " + resultado);

        // Exemplo 2
        Comparador<String> comparador = (String s1, String s2) -> s1.compareTo(s2);
        resultado = comparador.compare("maçã", "banana");
        System.out.println("Resultado: " + resultado);

        // Exemplo 3
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> pares = numeros.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        for (Integer i : pares) {
            System.out.print(i + " ");
        }

        /*
            >> Terminal
            Resultado: 8
            Resultado: 11
            2 4 6 8 10
        */
    }
}


