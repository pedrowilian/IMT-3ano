

public class Atribuicao {
    private Professor professor;
    private Disciplina disciplina;

    public Atribuicao(Professor professor, Disciplina disciplina){
        this.professor = professor;
        this.disciplina = disciplina;
    }
    //métodos modificadores
    public void setProfessor(Professor professor){
        this.professor = professor;
    }
    public void setDisciplina(Disciplina disciplina){
        this.disciplina = disciplina;
    }
    //métodos de acesso
    public Professor getProfessor(){
        return professor;
    }
    public Disciplina getDisciplina(){
        return disciplina;
    }
    //método getDados
    public String getDados(){
        return ""+professor.getDados()+"\n\n"
        +""+disciplina.getDados();
    }
    
}
