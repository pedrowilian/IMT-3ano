import javax.swing.JOptionPane;

public class TesteCelular {
    public static void main(String[] args) {
        String marca = JOptionPane.showInputDialog("Marca do celular:");
        String modelo = JOptionPane.showInputDialog("Modelo do celular:");
        int armazenamento = Integer.parseInt(JOptionPane.showInputDialog("Armazenamento (GB):"));

        Celular celular = new Celular(marca, modelo, armazenamento);

        String fichaTecnica = "FICHA TÉCNICA DO CELULAR\nMarca: " + celular.getMarca() + "\n" +
                              "Modelo: " + celular.getModelo() + "\n" +
                              "Armazenamento: " + celular.getArmazenamento() + " GB";

        JOptionPane.showMessageDialog(null, fichaTecnica);
    }
}
