package Aula104;

/**
 * Teste de PilhaInt: demonstra push, pop e impressão.
 */
public class TestPilhaInt {
    public static void main(String[] args) {
        PilhaInt pilha = new PilhaInt(3);
        pilha.imprimePilha();

        pilha.push(9);
        pilha.push(4);
        pilha.push(3);
        pilha.imprimePilha(); // deve mostrar 3 4 9

        pilha.pop();
        pilha.imprimePilha(); // 4 9
        pilha.pop();
        pilha.imprimePilha(); // 9
        pilha.pop();
        pilha.imprimePilha(); // vazia
    }
}
