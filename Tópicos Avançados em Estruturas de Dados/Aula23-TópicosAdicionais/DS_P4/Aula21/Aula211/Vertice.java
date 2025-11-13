package Aula211;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    // Atributos
    String dado;
    ArrayList<Aresta> listaArestas;

    // Construtor padrão
    public Vertice(String dado, ArrayList<Aresta> listaArestas) {
        this.dado = dado;
        this.listaArestas = listaArestas;
    }

    // Construtor parametrizado
    public Vertice(String dado) {
        this.dado = dado;
        this.listaArestas = new ArrayList<>();
    }

    // Getters
    public String getDado() {
        return dado;
    }
    public ArrayList<Aresta> getListaArestas() {
        return listaArestas;
    }

    // Setters
    public void setDado(String dado) {
        this.dado = dado;
    }
    public void setListaArestas(ArrayList<Aresta> listaArestas) {
        this.listaArestas = listaArestas;
    }



    // Adcionar Aresta ao Vertice
    public void addAresta(Aresta a) {
        this.listaArestas.add(a);
    }

    // Remover Aresta do Vertice
    public boolean removeAresta(Aresta a) {
        if (this.listaArestas == null) {
            return false;
        }
        return this.listaArestas.remove(a);
    }

    public List<Aresta> getArestas(){
        return listaArestas;
    }
}