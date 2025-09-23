package Ex4;

import javax.swing.*;

public class DepositoTestJOptionPane {

    public static void main(String[] args) {
        DepositoJOptionPane deposit = new DepositoJOptionPane();
        int op;
        int index;

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Adicionar caixa\n" +
                    "2 - Remover caixa\n" +
                    "3 - Procurar caixa\n" +
                    "4 - Mudar caixa\n" +
                    "5 - Listar caixas mais pesadas que 10.0\n" +
                    "6 - Sair\n" +
                    "Digite a opção: ", "Opções", JOptionPane.QUESTION_MESSAGE));

            switch (op) {
                case 1 -> deposit.addBox();
                case 2 -> deposit.removeBox();
                case 3 -> {
                    index = deposit.searchBox();
                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "Caixa não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Dados: \n"+deposit.boxes.get(index).getData());
                    }
                }
                case 4 -> deposit.changeBox();
                case 5 -> deposit.returnByBoxByWeight(10.0);
                case 6 -> JOptionPane.showMessageDialog(null, "Saindo...", "Saindo...", JOptionPane.INFORMATION_MESSAGE);
                default -> JOptionPane.showMessageDialog(null, "Opção inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            }


        }while(op != 6);
    }

}
