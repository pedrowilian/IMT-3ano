public class TestHashC {
    
    public static void main(String[] args) {
        Integer[] tabChaves = new Integer[] { 23, 45, 77, 11, 33, 49, 10, 4, 89, 14 };
        Integer[] tabHash = new Integer[10];
        
        // Inicializar a tabela hash com null (não necessário em Java, mas explícito)
        for (int i = 0; i < tabHash.length; i++) {
            tabHash[i] = null;
        }
        
        // Processar cada chave
        for (Integer chave : tabChaves) {
            Integer indiceHash = hash(chave);
            
            // Verificar se há colisão
            if (tabHash[indiceHash] == null) {
                // Posição livre, inserir a chave
                tabHash[indiceHash] = chave;
                System.out.println("Chave " + chave + " inserida no índice: " + indiceHash);
            } else {
                // Colisão detectada, aplicar rehashing
                Integer novoIndice = rehashing(tabHash, indiceHash);
                
                if (novoIndice != null) {
                    tabHash[novoIndice] = chave;
                    System.out.println("Chave " + chave + " inserida no índice (rehashing): " + novoIndice);
                } else {
                    System.out.println("Não foi possível inserir a chave " + chave + ". Tabela cheia!");
                }
            }
        }
        
        // Imprimir a tabela hash resultante
        System.out.println("\nTabela Hash Final:");
        for (int i = 0; i < tabHash.length; i++) {
            System.out.println("Índice " + i + ": " + tabHash[i]);
        }
    }
    
    // Método hash usando o método da divisão
    static Integer hash(Integer key) {
        return key % 10;
    }
    
    // Método rehashing para tratar colisões
    static Integer rehashing(Integer[] tabhash, Integer indice) {
        // Procurar posição livre a partir do próximo índice até o final
        for (Integer i = indice + 1; i < tabhash.length; i++) {
            if (tabhash[i] == null) {
                return i;
            }
        }
        
        // Se não encontrou, procurar do início até o índice anterior
        for (Integer i = 0; i < indice; i++) {
            if (tabhash[i] == null) {
                return i;
            }
        }
        
        // Tabela completamente cheia
        return null;
    }
}