package Ex3;

import java.util.Scanner;

class TesteBlocoDeNotas {
    public static void main(String[] args) {
        BlocoDeNotas bloco = new BlocoDeNotas();
        try (Scanner scanner = new Scanner(System.in)) {
            int op;
            
            do {
                System.out.println("1 - Adicionar nota");
                System.out.println("2 - Remover nota");
                System.out.println("3 - Listar notas");
                System.out.println("4 - Alterar nota");
                System.out.println("0 - Sair");
                System.out.print("Digite a opção desejada: ");
                op = scanner.nextInt();
                scanner.nextLine();
                
                
                
                switch (op)
                {
                    case 0 -> System.out.println("Saindo...");
                    
                    case 1 -> {
                        System.out.println("Digite a nota: ");
                        String note = scanner.nextLine();
                        bloco.addNote (note);
                    }
                    
                    case 2 -> {
                        bloco.listNotes();
                        System.out.println("Digite o número da nota ser removida: ");
                        int noteToRemove = scanner.nextInt();
                        bloco.removeNote(noteToRemove);
                    }
                    
                    case 3 -> bloco.listNotes();
                    
                    case 4 -> {
                        bloco.listNotes();
                        System.out.println("Digite o número da nota ser alterada: ");
                        int noteToChange = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.println("Digite a nova nota: ");
                        String newNote = scanner.nextLine();
                        
                        bloco.changeNote(noteToChange, newNote);
                    }
                    
                    default -> System.out.println("Opção inválida");
                    
                }
                
                
            }while (op != 0);
        }
    }
}
