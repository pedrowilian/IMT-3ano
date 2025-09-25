package Aula103;

// Classe de teste para CList (lista circular)

public class Test_CList {
    public static void main(String[] args) {
        System.out.println("=== Teste Lista Circular ===");
        CList c = new CList();
        c.imprimeFirst();
        c.imprimeLast();

        // insere no fim e testa
        c.insereLast(5);
        c.insereLast(3);
        c.insereLast(2);
        c.insereLast(4);
        c.insereLast(8);
        c.imprimeLista();

        // remove do fim
        c.deleteLast();
        c.imprimeLista();
        c.deleteLast();
        c.imprimeLista();

        // insere no início
        c.insereFirst(7);
        c.imprimeLista();
        c.imprimeFirst();
        c.imprimeLast();
    }
}
