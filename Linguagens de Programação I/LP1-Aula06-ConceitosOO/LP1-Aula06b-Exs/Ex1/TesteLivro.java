import javax.swing.JOptionPane;

public class TesteLivro {
        public static void main(String[] args) {
        // Entrada de dados
        String titulo = JOptionPane.showInputDialog("Título: ");
        String autor = JOptionPane.showInputDialog("Autor: ");
        int numeroDePaginas = Integer.parseInt(JOptionPane.showInputDialog("Número de páginas: "));

        // Criação do objeto Livro
        Livro livro = new Livro(titulo, autor, numeroDePaginas);
        // Monta a mensagem com os dados do livro
        String msg = "Título: " + livro.getTitulo() + 
                     "\nAutor: " + livro.getAutor() + 
                     "\nNúmero de páginas: " + livro.getNumeroDePaginas();
        
        JOptionPane.showMessageDialog(null, msg);
    }
}
