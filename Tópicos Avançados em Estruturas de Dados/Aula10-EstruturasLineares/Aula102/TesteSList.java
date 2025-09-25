package Aula102;

/**
 * Testa as operações de inserção, remoção, alteração e impressão
 * em uma lista simplesmente ligada (SList).
 */
public class TesteSList {
    public static void main(String[] args) {
        SList lista = new SList();
        System.out.println("### Teste de Lista Ligada ###");
        lista.imprimeLista();            // vazio
        lista.insereFim(10);             // insere no fim: 10
        lista.insereInicio(5);           // insere no início: 5 -> 10
        lista.insereFim(20);             // insere no fim: 5 -> 10 -> 20
        lista.imprimeLista();            // imprime
        
        lista.alteraInicio(1);           // altera primeiro: 1 -> 10 -> 20
        lista.alteraFim(99);             // altera fim: 1 -> 10 -> 99
        lista.imprimeLista();
        
        lista.deleteInicio();            // remove primeiro: 10 -> 99
        lista.deleteFim();               // remove último: 10
        lista.imprimeLista();
        
        System.out.printf("Comprimento final: %d\n", lista.comprimento());
    }
}
