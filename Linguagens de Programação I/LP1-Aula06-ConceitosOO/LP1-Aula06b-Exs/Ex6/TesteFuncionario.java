import javax.swing.JOptionPane;

public class TesteFuncionario {
     public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Nome do funcionário:");
        String cargo = JOptionPane.showInputDialog("Cargo:");
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Salário mensal:"));

        Funcionario funcionario = new Funcionario(nome, cargo, salario);

        String mensagem = "Nome: " + funcionario.getNome() +
                          "\nCargo: " + funcionario.getCargo() +
                          "\nSalário Mensal: R$ " + funcionario.getSalario() +
                          "\nSalário Anual: R$ " + funcionario.calcularSalarioAnual();

        JOptionPane.showMessageDialog(null, mensagem);
    }
}
