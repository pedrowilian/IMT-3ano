import javax.swing.JOptionPane;

public class TesteAnimal {
    public static void main(String[] args) {
        // Primeiro animal
        String especie1 = JOptionPane.showInputDialog("Espécie do primeiro animal:");
        String nome1 = JOptionPane.showInputDialog("Nome do primeiro animal:");
        int idade1 = Integer.parseInt(JOptionPane.showInputDialog("Idade do primeiro animal:"));

        Animal animal1 = new Animal(especie1, nome1, idade1);

        // Segundo animal
        String especie2 = JOptionPane.showInputDialog("Espécie do segundo animal:");
        String nome2 = JOptionPane.showInputDialog("Nome do segundo animal:");
        int idade2 = Integer.parseInt(JOptionPane.showInputDialog("Idade do segundo animal:"));

        Animal animal2 = new Animal(especie2, nome2, idade2);

        // Comparação de idade
        String comparacao;

        if (animal1.getIdade() > animal2.getIdade()) {
            comparacao = animal1.getNome() + " é mais velho que " + animal2.getNome();
        } else if (animal1.getIdade() < animal2.getIdade()) {
            comparacao = animal2.getNome() + " é mais velho que " + animal1.getNome();
        } else {
            comparacao = animal1.getNome() + " e " + animal2.getNome() + " têm a mesma idade.";
        }

        String mensagem = "Animal 1: " + animal1.getNome() + " (" + animal1.getEspecie() + ") - " + animal1.getIdade() + " anos\n" +
                          "Animal 2: " + animal2.getNome() + " (" + animal2.getEspecie() + ") - " + animal2.getIdade() + " anos\n\n" +
                          comparacao;

        JOptionPane.showMessageDialog(null, mensagem);
    }
}
