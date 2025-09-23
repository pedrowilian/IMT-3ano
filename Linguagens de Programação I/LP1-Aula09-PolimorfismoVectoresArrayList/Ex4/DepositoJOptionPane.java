package Ex4;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class DepositoJOptionPane extends Deposito {
    ArrayList<Caixa> boxes = new ArrayList<>();


    @Override
    public void addBox(){
        JOptionPane.showMessageDialog(null, "Adicionando caixa", "Adicionando caixa", JOptionPane.INFORMATION_MESSAGE);
        String corredor = JOptionPane.showInputDialog(null, "Digite o corredor: ", "Adicionando caixa", JOptionPane.QUESTION_MESSAGE);
        int posicao = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a posição: ", "Adicionando caixa", JOptionPane.QUESTION_MESSAGE));
        double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o peso: ", "Adicionando caixa", JOptionPane.QUESTION_MESSAGE));
        String owner = JOptionPane.showInputDialog(null, "Digite o dono: ", "Adicionando caixa", JOptionPane.QUESTION_MESSAGE);

        Caixa box = new Caixa(corredor, posicao, peso, owner);

        boxes.add(box);
    }

    @Override
    public void printBoxes(){
        for (Caixa box : boxes) {
            System.out.println(box.getData());
        }
    }

    @Override
    public void removeBox(){
        String owner = JOptionPane.showInputDialog(null, "Digite o dono: ", "Removendo caixa", JOptionPane.QUESTION_MESSAGE);

        for (Caixa box : boxes) {
            if(box.getDono().equals(owner)){
                boxes.remove(box);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Caixa não encontrada", "Removendo caixa", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public int searchBox(){
        String owner = JOptionPane.showInputDialog(null, "Digite o dono: ", "Removendo caixa", JOptionPane.QUESTION_MESSAGE);

        for (int i = 0; i < boxes.size(); i++) {
            if(boxes.get(i).getDono().equals(owner)){
                return i;
            }
        }
        return -1;

    }

    @Override
    public void changeBox(){
        try (Scanner scanner = new Scanner(System.in)) {
            int indexBox = searchBox();
            
            if (indexBox == -1){
                JOptionPane.showMessageDialog(null, "Caixa não encontrada", "Alterando caixa", JOptionPane.ERROR_MESSAGE);
            }
            Caixa box = boxes.get(indexBox);
            
            String corredor = JOptionPane.showInputDialog(null, "Digite o novo corredor: ", "Alterando caixa", JOptionPane.QUESTION_MESSAGE);
            int posicao = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a nova posição: ", "Alterando caixa", JOptionPane.QUESTION_MESSAGE));
            double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o novo peso: ", "Alterando caixa", JOptionPane.QUESTION_MESSAGE));
            String novoDono = JOptionPane.showInputDialog(null, "Digite o novo dono: ", "Alterando caixa", JOptionPane.QUESTION_MESSAGE);
            
            box.setCorredor(corredor);
            box.setPosicao(posicao);
            box.setPeso(peso);
            box.setDono(novoDono);
        }
        }

        public void returnByBoxByPeso(double peso){
            String allBoxes = "";
            for (Caixa box : boxes) {
                if(box.getPeso() > peso){
                    allBoxes += box.getData() + "\n";
                }
            }
            JOptionPane.showMessageDialog(null, allBoxes, "Caixas com peso maior que " + peso, JOptionPane.INFORMATION_MESSAGE);
            
        }
}
