import javax.swing.JOptionPane;

public class TesteContaBancaria {
    public static void main(String[] args) {
        // Entradas iniciais
        String numero = JOptionPane.showInputDialog("Número da conta:");
        String titular = JOptionPane.showInputDialog("Nome do titular:");
        double saldoInicial = Double.parseDouble(JOptionPane.showInputDialog("Saldo inicial:"));

        // Criação da conta
        ContaBancaria conta = new ContaBancaria(numero, titular, saldoInicial);

        // Operação de depósito
        double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Valor para depósito:"));
        conta.depositar(valorDeposito);

        // Operação de saque
        double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Valor para saque:"));
        boolean sucesso = conta.sacar(valorSaque);

        // Mensagem final
        String mensagem = "Conta: " + conta.getNumeroConta() +
                          "\nTitular: " + conta.getNomeTitular() +
                          "\nSaldo atual: R$" + conta.getSaldo();

        if (!sucesso) {
            mensagem += "\n(Saque não realizado: saldo insuficiente)";
        }

        JOptionPane.showMessageDialog(null, mensagem);
    }
}
