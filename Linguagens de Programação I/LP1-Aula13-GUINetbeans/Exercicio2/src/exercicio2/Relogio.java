package exercicio2;

public class Relogio {
    public Mostrador hora;        // Mostrador das horas
    public Mostrador minuto;      // Mostrador dos minutos
    public String mostrador;      // Representação atual do relógio em string

    public Relogio(){
        hora = new Mostrador(24);     // Cria mostrador com limite 24 horas
        minuto = new Mostrador(60);   // Cria mostrador com limite 60 minutos
        atualizaMostrador();                 // Atualiza a string mostrador
    }

    // Avança um minuto; se minuto chegar a 0, incrementa a hora
    public void ticTac(){
        minuto.incrementar();
        if(minuto.getValor() == 0){
            hora.incrementar();
        }
        atualizaMostrador();
    }

    // Atualiza a string que representa o horário
    public void atualizaMostrador(){
        mostrador = hora.mostra() + ":" + minuto.mostra();
    }

    // Retorna a string do horário atual
    public String mostra(){
        return mostrador;
    }
}

