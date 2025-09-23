package Ex3;

public class Main {
    public static void main(String[] args) {
        Agencia agencia = new Agencia("Banco-Cliente", 752);
        ContaCorrente conta = new ContaCorrente(1230, agencia);
        System.out.println(conta.imprimirSaldo());
        Cliente client = new  Cliente("Evandro Calvetti", "12345678910", conta);
        System.out.println(client.toString());

        conta.depositar(100);

        if(conta.sacar(50)  == 1){
            System.out.println("Saque realizado com sucesso");
        }
        else {
            System.out.println("Saldo insuficiente");
        }

        System.out.println(conta.imprimirSaldo());

        if(conta.sacar(100)  == 1){
            System.out.println("Saque realizado com sucesso");
        }
        else {
            System.out.println("Saldo insuficiente");
        }

        System.out.println(conta.imprimirSaldo());
    }

}
