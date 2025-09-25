package Aula103;

// Demonstra operações de DList (lista duplamente ligada)
// Complexidade geral de execução depends on each op.

public class TesteDList {
    public static void main(String[] args) {
        System.out.println("=== Teste Lista Duplamente Ligada ===");
        DList lista = new DList();
        lista.imprimeLista(); // vazio
        // inserções
        lista.insereFim(5);
        lista.insereInicio(3);
        lista.insereFim(7);
        lista.insereInicio(1);
        lista.imprimeLista();
        lista.imprimeLista2();
        // deleções
        lista.deleteFirst();
        lista.imprimeLista();
        lista.deleteLast();
        lista.imprimeLista();
    }
}
