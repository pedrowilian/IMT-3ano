package Ex1;


import javamysqlapp.ConnFactory;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

public class Main {
    static final String sOptions[] = {"Inserir", "Alterar", "Excluir", "Consultar", "Listar todos", "Sair"};

    public static void main(String[] args) {
        Gui gui = new Gui(sOptions);
        int iOption = gui.getOption();
        Professor professor;


        CrudProfessor crudProfessor = new CrudProfessor();

        Connection conn = ConnectionFactory.getConn();
        while(iOption != sOptions.length-1)
        {
            System.out.println("Opção: " + iOption);
            switch(iOption)
            {   case 0:
                    String nome = JOptionPane.showInputDialog("Digite o nome do professor");
                    int matricula = Integer.parseInt(JOptionPane.showInputDialog("Digite a matricula do professor"));
                    int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do professor"));
                    professor = new Professor(nome, matricula, idade);
                    crudProfessor.inserir(conn, professor);
                    break;
                case 1: // Alterar
                    int matriculaAlterar = Integer.parseInt(JOptionPane.showInputDialog("Digite a matricula do professor"));
                    String nomeAlterar = JOptionPane.showInputDialog("Digite o novo nome do professor");
                    int idadeAlterar = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova idade do professor"));

                    professor = new Professor(nomeAlterar, matriculaAlterar, idadeAlterar);
                    crudProfessor.alterar(conn, professor);
                    break;
                case 2:
                    int matriculaExcluir = Integer.parseInt(JOptionPane.showInputDialog("Digite a matricula do professor"));
                    crudProfessor.excluir(conn, matriculaExcluir);
                    break;
                case 3:
                    int matriculaConsultar = Integer.parseInt(JOptionPane.showInputDialog("Digite a matricula do professor"));
                    professor = crudProfessor.consultar(conn, matriculaConsultar);
                    System.out.println(professor);
                    break;
                case 4:
                    ArrayList<Professor> listProfessor = crudProfessor.listaTodos(conn);
                    for(Professor p : listProfessor)
                    {   System.out.println(p);
                        System.out.println("-------------------------------");
                    }


                    break;
            }
            iOption = gui.getOption();
        }
        ConnFactory.closeConn(conn);


    }

}
