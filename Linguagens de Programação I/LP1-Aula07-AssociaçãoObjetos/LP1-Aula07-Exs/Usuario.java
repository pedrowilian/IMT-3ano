public class Usuario {
    public static void main(String[] args) {
        CondicionadorDeAr condicionadorDeAr = new CondicionadorDeAr();
        
        if(condicionadorDeAr.getLigado()){
            //aumentar até 28°C 
            condicionadorDeAr.aumentarTemperatura(30);
            //diminuir até 10°C 
            condicionadorDeAr.diminuirTemperatura(10);
            //aumentar para 25°C
            condicionadorDeAr.aumentarTemperatura(25);
            condicionadorDeAr.desligar();
        }
        else{
            condicionadorDeAr.ligar();
             //aumentar até 28°C 
             condicionadorDeAr.aumentarTemperatura(30);
             //diminuir até 15°C 
             condicionadorDeAr.diminuirTemperatura(10);
             //aumentar para 25°C
             condicionadorDeAr.aumentarTemperatura(25);
        }
        
        
    }
    
}
