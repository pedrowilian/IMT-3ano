public class TestHash {
    public static void main(String[] args) {
        //Criar as chaves para colocar no hash table de 1 a 19
        Integer[] tabKeys = new Integer[20];   
        // Hash table with 10 slots all slots are initially null linked list
        SList[] tabHash = new SList[10];
        // Initialize linked lists for each slot starting from 1
        for (int i = 1; i < tabHash.length; i++) 
            tabHash[i] = new SList();

        
        for (int i = 1; i < tabKeys.length; i++) {
            System.out.println("Chave: " + i + ", Hashcode: " + hash(i));
        }


            // // Disregard the first array position (index 0)
            // for (int i = 1; i < tabKeys.length; i++) {
            //     int idx = hash(tabKeys[i]);
            //     System.out.println("Chave: " + tabKeys[i] + ", Hashcode: " + idx);
            //     // Use associative array: associate key with name (cycling through tabNomes for demonstration)
            //     tabHash[idx] = tabNomes[i % tabNomes.length];
            // }
            // for (int i = 1; i < tabHash.length; i++) {
            //     if (tabHash[i] != null) {
            //         System.out.println("Índice: " + i + "    ==> Valor armazenado na tabela hash: " + tabHash[i]);
            //     }
            // }
    }

    public static Integer hash(Integer key) {
        return key % 10;
    }
}