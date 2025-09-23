import javax.swing.JOptionPane;

public class CaixaEletronico {
     public static void main(String[] args) {
        int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta (sem o dígito, de quatro algarismos e positivo): ","Número", JOptionPane.QUESTION_MESSAGE ));
        
        //lógica do módulo 11 para o dígito da conta
        int digito = ((numero/1000)*4+ ((numero%1000)/100)*6 + ((numero%100)/10)*8 + (numero%10)*2)%11 ;
        
        //variáveis para agência 
        int numero_agencia = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da agência (sem o dígito, de quatro algarismos e positivo): ","Número", JOptionPane.QUESTION_MESSAGE ));
        int digito_agencia = ((numero_agencia/1000)*4+ ((numero_agencia%1000)/100)*6 + ((numero_agencia%100)/10)*8 + (numero_agencia%10)*2)%11;
        String nome_agencia = JOptionPane.showInputDialog(null, "Nome da agência: ");
        //variáveis para cliente
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente");
        double saldo = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o saldo atual da conta"));
        String cpf = JOptionPane.showInputDialog(null, "Digite seu cpf: ");

        //criação dos objetos
        Agencia agencia = new Agencia(nome_agencia, numero_agencia, digito_agencia);
        ContaCorrente contaCorrente = new ContaCorrente(numero, digito, agencia, saldo);
        Cliente cliente = new Cliente(nome, cpf, contaCorrente);

        String message ="Nome: "+cliente.getNome()+
                        "\n"+"CPF: "+cliente.getCpf()+"\n"+
                        "Conta Corrente: "+contaCorrente.getNumero()+" Dígito: "+contaCorrente.getDigito()
                        +"\n" + "Agência: "+agencia.getNumero()+" Dígito: "+agencia.getDigito()
                        +"\n"+"Saldo inicial: "+contaCorrente.getSaldo();

        JOptionPane.showMessageDialog(null, message);
        int op = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite uma opção:\n\n1: Saque\n2: Depósito\n-1: Sair"));
                        
        do {
            switch (op) {
                case 1: {
                    double saque = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor sacado"));
                    contaCorrente.sacar(saque);
                    JOptionPane.showMessageDialog(null, contaCorrente.consultarSaldo());
                    op = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite uma opção: \n\n1: Saque\n2: Depósito\n-1: Sair"));
                }
                case 2: {
                    double deposito = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor depositado"));
                    contaCorrente.depositar(deposito);
                    JOptionPane.showMessageDialog(null, contaCorrente.consultarSaldo());
                    op = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite uma opção: \n\n1: Saque\n2: Depósito\n-1: Sair"));
                }
            }
        } while (op!=-1);
        JOptionPane.showMessageDialog(null, contaCorrente.imprimirSaldo());
    }
}
