package textfield;

import javax.swing.*;

import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TextFieldFrame extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField;

    public TextFieldFrame()
    {
        super("JTextField e JPasswordField Teste");
        setLayout(new FlowLayout());
        textField1 = new JTextField(10);
        add(textField1);
        textField2 = new JTextField("Entre com o texto aqui", 10);
        add(textField2);
        textField3 = new JTextField("Campo texto não editável", 20);
        textField3.setEditable(false);
        add(textField3);
        passwordField = new JPasswordField("Texto oculto", 10);
        add(passwordField);
        TextFieldHandler handler = new TextFieldHandler();
        textField1.addActionListener(handler);
        textField2.addActionListener(handler);
        textField3.addActionListener(handler);
        passwordField.addActionListener(handler);
    }

    // Inner class to handle text field actions
    private class TextFieldHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String string = "";
            if (event.getSource() == textField1)
                string = String.format("textField1: %s", event.getActionCommand());
            else if (event.getSource() == textField2)
                string = String.format("textField2: %s", event.getActionCommand());
            else if (event.getSource() == textField3)
                string = String.format("textField3: %s", event.getActionCommand());
            else if (event.getSource() == passwordField)
                string = String.format("passwordField: %s", new String(passwordField.getPassword()));

            JOptionPane.showMessageDialog(null, string);
        }
    }
}
