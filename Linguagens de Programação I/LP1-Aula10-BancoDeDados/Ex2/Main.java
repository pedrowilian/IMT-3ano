package Ex2;

import Ex1.ConnectionFactory;
import Ex1.CrudProfessor;
import Ex1.Professor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int op;
        Scanner scanner = new Scanner(System.in);
        String nome;
        String codigo;
        Disciplina disciplina;
        Professor professor;
        ArrayList<Professor> professores;
        int matriculaProfessor;

        CrudProfessor crudProfessor = new CrudProfessor();
        CrudDisciplina crudDisciplina = new CrudDisciplina();
        Connection conn = ConnectionFactory.getConn();

        do {
            System.out.println("1 - Inserir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Consultar");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            op = scanner.nextInt();
            scanner.nextLine();
            switch (op) {
                case 1:
                    System.out.println("Inserir");
                    disciplina = new Disciplina();
                    System.out.print("Digite o nome da disciplina: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite o código da disciplina: ");
                    codigo = scanner.nextLine();
                    disciplina.setNome(nome);
                    disciplina.setCodigo(codigo);

                    System.out.print("Digite o número de professores: ");
                    int nProfessores = scanner.nextInt();
                    for (int i = 0; i < nProfessores; i++) {
                        System.out.print("Digite a matrícula do professor: ");
                        matriculaProfessor = scanner.nextInt();
                        professor = crudProfessor.consultar(conn, matriculaProfessor);
                        while (professor == null) {
                            System.out.println("Professor não encontrado");
                            System.out.print("Digite a matrícula do professor: ");
                            matriculaProfessor = scanner.nextInt();
                            professor = crudProfessor.consultar(conn, matriculaProfessor);
                        }
                        disciplina.addProfessor(professor);
                    }
                    crudDisciplina.inserir(conn, disciplina);
                    break;
                case 2:
                    System.out.println("Alterar");
                    System.out.print("Digite o código da disciplina: ");
                    codigo = scanner.next();
                    disciplina = crudDisciplina.consultar(conn, codigo);
                    if (disciplina != null) {
                        System.out.print("Digite o novo nome da disciplina: ");
                        nome = scanner.next();
                        disciplina.setNome(nome);
                        crudDisciplina.alterar(conn, disciplina);
                    } else {
                        System.out.println("Disciplina não encontrada");
                    }

                    break;
                case 3:
                    System.out.println("Excluir");
                    System.out.print("Digite o código da disciplina: ");
                    codigo = scanner.next();
                    crudDisciplina.excluir(conn, codigo);
                    break;
                case 4:
                    System.out.println("Consultar");
                    System.out.print("Digite o código da disciplina: ");
                    codigo = scanner.next();
                    disciplina = crudDisciplina.consultar(conn, codigo);
                    if (disciplina != null) {
                        System.out.println(disciplina.toString());
                    } else {
                        System.out.println("Disciplina não encontrada");
                    }

                    break;
                case 0:
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        } while (op != 0);
    }
}
