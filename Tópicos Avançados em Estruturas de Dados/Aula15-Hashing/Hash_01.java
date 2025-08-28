public class Hash_01 {

    public static void main(String[] args) {

        Aluno[] tabAluno = new Aluno[10];

        tabAluno[0] = new Aluno(10, "Ana");
        tabAluno[1] = new Aluno(21, "Silas");
        tabAluno[2] = new Aluno(22, "Ari");
        tabAluno[3] = new Aluno(24, "Pedro");
        tabAluno[4] = new Aluno(35, "Jonas");
        tabAluno[5] = new Aluno(60, "Saul");
        tabAluno[6] = new Aluno(44, "Josue");
        tabAluno[7] = new Aluno(57, "Paulo");
        tabAluno[8] = new Aluno(80, "Sara");
        tabAluno[9] = new Aluno(90, "Davi");

        Integer hashCode = null, chave;
        Aluno[] tabHash = new Aluno[10];

        for (int i = 0; i < tabAluno.length; i++) {
            chave = (tabAluno[i].getCodAluno());
            hashCode = hash(chave);
            System.out.println("Chave = " + chave +
                               " mapeada para hashcode = " + hashCode);

            if (tabHash[hashCode] == null)
                tabHash[hashCode] = tabAluno[i];
            else {
                System.out.println("** Colisao no slot da Tabela Hash **");
                System.out.println("Chave " + tabAluno[i].getCodAluno() +
                                   " NAO ARMAZENADA NA TABELA HASH ...\n");
            }
        }

        System.out.println("\nTabela Aluno: ");
        System.out.println("---------------------------");
        for (int i = 0; i < tabAluno.length; i++) {
            System.out.print("Slot " + i + " ---> " + tabAluno[i].getCodAluno()+ 
                                " " + tabAluno[i].getNome() + '\n');
        }

        System.out.println("\nTabela HASH: ");
        System.out.println("---------------------------");
        for (int i = 0; i < tabHash.length; i++) {
            if (tabHash[i] == null)
                System.out.println("Slot " + i + " ---> Valor nulo");
            else
                System.out.print("Slot " + i + " ---> " +
                                  tabHash[i].getCodAluno() + " " +
                                  tabHash[i].getNome() + '\n');
        }
    }

    // Função de hash (incompleta na sua imagem)
    public static Integer hash(Integer key) {
        return key % 10;
    }
}
