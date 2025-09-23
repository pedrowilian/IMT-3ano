package Ex3e4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEmpregados extends JFrame implements ActionListener {

    private JButton botaoAdicionar;
    private JPanel painelBotoes;
    private JPanel painelTabela;
    private JTable tabela;
    private JScrollPane barraRolagem;

    private String[] colunas = {"Nome", "Sobrenome", "CPF", "Renda", "Tipo de Empregado"};

    public TelaEmpregados() {
        this.setTitle("Cadastro de Empregados");
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);

        painelBotoes = new JPanel(new GridLayout(1, 2));
        botaoAdicionar = new JButton("Adicionar Empregado");
        botaoAdicionar.addActionListener(this);
        painelBotoes.add(botaoAdicionar);

        add(painelBotoes, constraints);

        GridBagConstraints tabelaConstraints = new GridBagConstraints();
        tabelaConstraints.gridx = 0;
        tabelaConstraints.gridy = 1;
        tabelaConstraints.anchor = GridBagConstraints.CENTER;
        tabelaConstraints.insets = new Insets(5, 5, 5, 5);

        painelTabela = new JPanel(new GridLayout(1, 1));
        tabela = new JTable(new DefaultTableModel(new String[][]{}, colunas));
        barraRolagem = new JScrollPane(tabela);
        painelTabela.add(barraRolagem);

        add(painelTabela, tabelaConstraints);

        this.pack();
        this.setLocationRelativeTo(null); // Centraliza
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoAdicionar) {
            try {
                String nome = JOptionPane.showInputDialog("Digite o nome:");
                String sobrenome = JOptionPane.showInputDialog("Digite o sobrenome:");
                String cpf = JOptionPane.showInputDialog("Digite o CPF:");
                String tipo = JOptionPane.showInputDialog("Digite o tipo de empregado (horista, tarefeiro, mensalista, comissionado, desempregado):").toLowerCase();

                PessoaFisica empregado = null;

                switch (tipo) {
                    case "horista":
                        double horas = Double.parseDouble(JOptionPane.showInputDialog("Horas trabalhadas:"));
                        double valorHora = Double.parseDouble(JOptionPane.showInputDialog("Valor por hora:"));
                        empregado = new EmpregadoHorista(nome, sobrenome, cpf, horas, valorHora);
                        break;
                    case "tarefeiro":
                        double tarefas = Double.parseDouble(JOptionPane.showInputDialog("Tarefas realizadas:"));
                        double valorTarefa = Double.parseDouble(JOptionPane.showInputDialog("Valor por tarefa:"));
                        empregado = new EmpregadoTarefeiro(nome, sobrenome, cpf, tarefas, valorTarefa);
                        break;
                    case "mensalista":
                        double salarioMensal = Double.parseDouble(JOptionPane.showInputDialog("Salário mensal:"));
                        empregado = new EmpregadoMensalista(nome, sobrenome, cpf, salarioMensal);
                        break;
                    case "comissionado":
                        double vendas = Double.parseDouble(JOptionPane.showInputDialog("Valor das vendas:"));
                        double comissao = Double.parseDouble(JOptionPane.showInputDialog("Comissão:"));
                        empregado = new EmpregadoComissionado(nome, sobrenome, cpf, vendas, comissao);
                        break;
                    case "desempregado":
                        double seguro = Double.parseDouble(JOptionPane.showInputDialog("Valor do seguro desemprego:"));
                        empregado = new Desempregado(nome, sobrenome, cpf, seguro);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Tipo inválido!");
                        return;
                }

                if (empregado != null) {
                    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                    modelo.addRow(empregado.listarDados());
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor numérico inválido!");
            }
        }
    }
}
