package model;

import java.sql.Timestamp;

public class Nota {
    private int id;
    private String user;
    private String disciplina;
    private float nota;
    private int faltas;
    private Timestamp ultimaAlteracao;

    public Nota(int id, String user, String disciplina, float nota, int faltas, Timestamp ultimaAlteracao) {
        this.id = id;
        this.user = user;
        this.disciplina = disciplina;
        this.nota = nota;
        this.faltas = faltas;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    // Getters e setters
    public int getId() { return id; }
    public String getUser() { return user; }
    public String getDisciplina() { return disciplina; }
    public float getNota() { return nota; }
    public void setNota(float nota) { this.nota = nota; }
    public int getFaltas() { return faltas; }
    public void setFaltas(int faltas) { this.faltas = faltas; }
    public Timestamp getUltimaAlteracao() { return ultimaAlteracao; }
    public void setUltimaAlteracao(Timestamp ultimaAlteracao) { this.ultimaAlteracao = ultimaAlteracao; }
    public void setId(int id) {this.id = id;} 
}
