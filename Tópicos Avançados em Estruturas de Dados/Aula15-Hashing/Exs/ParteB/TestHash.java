// Hash table implementation using linked lists for collisions

public class TestHash {
    public static void main(String[] args) {
        //Criar as chaves para colocar no hash table de 1 a 19
        Integer[] tabKeys = new Integer[100000];
        for (int i = 1; i < tabKeys.length; i++) 
            tabKeys[i] = i;
        // Hash table with 10 slots all slots are initially null linked list
        SList[] tabHash = new SList[10];
        // Initialize linked lists for each slot starting from 1
        for (int i = 0; i < tabHash.length; i++) 
            tabHash[i] = new SList();

        for (int i = 1; i < 1000; i++) {
            // Insert keys into the hash table
            int aux = tabKeys[i];
            int idx = hash(aux);
            tabHash[idx].insereInicio(aux);
            System.out.println("Chave: " + aux + ", Hashcode: " + idx);
        }
        // Print the hash table
        System.out.println("\nHash Table:");
        for (int i = 0; i < tabHash.length; i++) {
            System.out.print("Índice " + i + ": ");
            tabHash[i].printList();
        }
    }

    public static Integer hash(Integer key) {
        return key % 10;
    }
}