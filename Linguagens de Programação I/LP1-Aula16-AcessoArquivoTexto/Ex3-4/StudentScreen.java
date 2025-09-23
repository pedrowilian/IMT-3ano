import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StudentScreen extends JFrame {
    JTextField raField;
    JTextField surnameField;
    JTextField nameField;
    JTextField p1Field;
    JTextField p2Field;
    JTextField p3Field;
    JTextField p4Field;

    TextFileHandler textFileHandler;

    public StudentScreen(TextFileHandler TextFile){
        super("Register User Screen");

        this.textFileHandler = TextFile;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(300, 300);
        JPanel panel0 = new JPanel();
        JLabel label = new JLabel("Register User Screen");
        label.setFont(new java.awt.Font("Tahoma", 0, 24));
        panel0.add(label);
        add(panel0);

        JPanel panel1 = new JPanel();
        TextFieldHandler handler = new TextFieldHandler();
        panel1.add(new JLabel("Ra: "));
        raField = new JTextField(10);
        raField.addActionListener(handler);
        panel1.add(raField);
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Nome: "));
        nameField = new JTextField(10);
        nameField.addActionListener(handler);

        panel2.add(nameField);
        panel2.setSize(300, 80);
        add(panel2);



        JPanel panel3 = new JPanel();
        surnameField = new JTextField(10);
        surnameField.addActionListener(handler);
        panel3.add(new JLabel("Sobrenome: "));
        panel3.add(surnameField);
        add(panel3);

        JPanel panel4 = new JPanel();
        p1Field = new JTextField(10);
        p1Field.addActionListener(handler);
        panel4.add(new JLabel("P1: "));
        panel4.add(p1Field);
        p2Field = new JTextField(10);
        p2Field.addActionListener(handler);
        panel4.add(new JLabel("P2: "));
        panel4.add(p2Field);
        p3Field = new JTextField(10);
        p3Field.addActionListener(handler);
        panel4.add(new JLabel("P3: "));
        panel4.add(p3Field);
        p4Field = new JTextField(10);
        p4Field.addActionListener(handler);
        panel4.add(new JLabel("P4: "));
        panel4.add(p4Field);
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        add(panel4);



        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        JButton register = new JButton("Register");
        panel5.add(register);
        register.addActionListener(
                e -> {
                    String name = nameField.getText();
                    String surname = surnameField.getText();
                    String ra = raField.getText();
                    double p1 = Double.parseDouble(p1Field.getText());
                    double p2 = Double.parseDouble(p2Field.getText());
                    double p3 = Double.parseDouble(p3Field.getText());
                    double p4 = Double.parseDouble(p4Field.getText());

                    register(name, surname, ra, p1, p2, p3, p4);



                }
        );
        JButton show = new JButton("Show");
        panel5.add(show);
        show.addActionListener(
                e -> {
                    String ra = raField.getText();
                    this.showUser(ra);

                }
        );
        add(panel5);


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

     private class TextFieldHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){

            String name = nameField.getText();
            String surname = surnameField.getText();
            String ra = raField.getText();
            double p1 = Double.parseDouble(p1Field.getText());
            double p2 = Double.parseDouble(p2Field.getText());
            double p3 = Double.parseDouble(p3Field.getText());
            double p4 = Double.parseDouble(p4Field.getText());

            register(name, surname, ra, p1, p2, p3, p4);

        }
    }

    public void register(String name, String surname, String ra, double p1, double p2, double p3, double p4){
        if (name.isEmpty() || surname.isEmpty() || ra.isEmpty() || p1Field.getText().isEmpty() || p2Field.getText().isEmpty() || p3Field.getText().isEmpty() || p4Field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                System.out.println("Cadastrando aluno...");
                Student student = new Student(ra, name, surname, p1, p2, p3, p4);
                textFileHandler.addStudentRecord(student);
                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
            }

    }

    public void showUser(String ra){
        Student student = textFileHandler.readStudentRecord(ra);
        if (student == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
        } else {
            JOptionPane.showMessageDialog(null, student.toString());
        }
    }
}


