package Ex2;

public class Relogio {
    public  Mostrador hora;
    public  Mostrador minuto;
    public String mostrador;

    public Relogio(){
        hora = new Mostrador(24);
        minuto = new Mostrador(60);
        atualizaMostrador();

    }

    public void ticTac(){
        minuto.incrementar();
        if(minuto.getValor() == 0){
            hora.incrementar();
        }
        atualizaMostrador();
    }

    public void atualizaMostrador(){
        mostrador = hora.mostra() + ":" + minuto.mostra();
    }

    public String mostra(){
        return mostrador;
    }

}
