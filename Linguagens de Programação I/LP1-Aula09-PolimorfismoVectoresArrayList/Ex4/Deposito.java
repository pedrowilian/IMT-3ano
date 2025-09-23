package Ex4;

import java.util.ArrayList;
import java.util.Scanner;

public class Deposito {
    ArrayList <Caixa> boxes = new ArrayList<>();


    public void addBox(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o corredor: ");
            String aisle = scanner.nextLine();
            System.out.print("Digite a posição: ");
            int row = scanner.nextInt();
            System.out.print("Digite o peso: ");
            double weight = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Digite o dono: ");
            String owner = scanner.nextLine();

            Caixa box = new Caixa(aisle, row, weight, owner);

            boxes.add(box);
        
    }

    public void printBoxes(){
        for (Caixa box : boxes) {
            System.out.println(box.getData());
        }
    }

    public void removeBox(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o dono: ");
            String owner = scanner.nextLine();
            
            for (Caixa box : boxes) {
                if(box.getDono().equals(owner)){
                    boxes.remove(box);
                    return;
                }
            }
            System.out.println("Caixa não encontrada");
        
    }

    public int searchBox(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o dono: ");
            String owner = scanner.nextLine();
            
            
            for (int i = 0; i < boxes.size(); i++) {
                if(boxes.get(i).getDono().equals(owner)){
                    return i;
                }
            }
        
        return -1;
    }

    public void changeBox(){

        Scanner scanner = new Scanner(System.in);
        int indexBox = searchBox();
            
        if (indexBox == -1){
            System.out.println("Caixa não encontrada");
        }
        Caixa box = boxes.get(indexBox);
            
        System.out.print("Digite o novo corredor: ");
        String corredor = scanner.nextLine();
        System.out.print("Digite a nova posição: ");
        int posicao = scanner.nextInt();
        System.out.print("Digite o novo peso: ");
        double peso = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Digite o novo dono: ");
        String novoDono = scanner.nextLine();
            
        box.setCorredor(corredor);
        box.setPosicao(posicao);
        box.setPeso(peso);
        box.setDono(novoDono);
        
        }

        public void returnByBoxByWeight(double peso){
            for (Caixa box : boxes) {
                if(box.getPeso() > peso){
                    System.out.println(box.getData());
                }
            }
        }


}
