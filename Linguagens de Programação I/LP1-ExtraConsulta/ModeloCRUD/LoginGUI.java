// ------------------------------
// 🔹 Tela de Login
// ------------------------------


import java.awt.*;
import javax.swing.*;

class LoginGUI extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginGUI() {
        setTitle("Login");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        btnLogin = new JButton("Login");
        add(btnLogin);

        // Botão vazio só para preencher grid
        add(new JLabel(""));

        // Validação simples (usuário = admin / senha = 123)
        btnLogin.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtSenha.getPassword());

            if (user.equals("admin") && pass.equals("123")) {
                new CrudGUI().setVisible(true);
                dispose(); // fecha tela de login
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas!");
            }
        });
    }
}