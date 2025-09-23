package Ex2;

import Ex1.Professor;

import java.util.ArrayList;

public class Disciplina {
    private String nome;
    private String codigo;
    private ArrayList<Professor> professores;

    Disciplina(){
        this.nome = "";
        this.codigo = "";
        this.professores = new ArrayList<>();
    }

    Disciplina(String codigo){
        this.nome = "";
        this.codigo = codigo;
        this.professores = new ArrayList<>();
    }

    Disciplina(String nome, String codigo, ArrayList<Professor> professores){
        this.nome = nome;
        this.codigo = codigo;
        this.professores = professores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }

    public void addProfessor(Professor professor){
        this.professores.add(professor);
    }

    public String toString(){
        StringBuilder str = new StringBuilder("Nome: " + this.nome + "\nCódigo: " + this.codigo + "\nProfessores: ");
        for (Professor professor : this.professores) {
            str.append("\n").append(professor.toString());
        }
        return str.toString();
    }
}

