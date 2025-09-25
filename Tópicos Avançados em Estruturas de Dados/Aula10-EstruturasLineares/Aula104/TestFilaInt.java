package Aula104;

/**
 * Teste de FilaInt: demonstra enqueue, dequeue e impressão.
 */
public class TestFilaInt {
    public static void main(String[] args) {
        FilaInt fila = new FilaInt(5);
        fila.imprimeFila();

        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        fila.enqueue(4);
        fila.enqueue(5);
        fila.imprimeFila();

        fila.dequeue();
        fila.imprimeFila();
        fila.dequeue();
        fila.imprimeFila();
    }
}
