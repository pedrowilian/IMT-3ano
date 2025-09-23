import javax.swing.JOptionPane;

public class CondicionadorDeAr {
    private Termostato termostato;
    private boolean ligado;

    public CondicionadorDeAr(){
        ligado = false;
        termostato = new Termostato();
    }
    //métodos de acesso
    public Termostato getTermostato(){
        return termostato;
    }
    public boolean getLigado(){
        return ligado;
    }
    public void ligar(){
        ligado = true;
    }
    public void desligar(){
        ligado = false;
    }
    public void imprimirTemperatura(){
        System.out.println(termostato.getTemperatura());
    }
    public void aumentarTemperatura(int max){
        for(int i=termostato.getTemperatura(); i<max; i++){
            if(max==25){
                termostato.setTemperatura(25);
            }
            else{
                termostato.setTemperatura(i);
            }
            
        }
        JOptionPane.showMessageDialog(null, "Não foi possível atingir 30°C","ERRO",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null, termostato.getTemperatura(), "Temperatura atual", JOptionPane.INFORMATION_MESSAGE);
    }
    public void diminuirTemperatura(int min){
        for(int i = termostato.getTemperatura(); i>min; i--){
            termostato.setTemperatura(i);
        }
        JOptionPane.showMessageDialog(null, "Não foi possível diminuir para 10°C","ERRO",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null, termostato.getTemperatura(), "Temperatura atual", JOptionPane.INFORMATION_MESSAGE);
    }
}
