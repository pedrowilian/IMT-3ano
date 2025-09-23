package Ex5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Botoes extends JPanel implements ActionListener {

    String[] botoesTextList = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "C", "=", "/"};
    Display display;
    String displayText = "0";
    String op = "";
    boolean op_happening = false;
    int ans = 0;
    int num = 0;

    Botoes(Display display) {
        super(new GridLayout(4, 4, 5, 5));

        for (String buttonText : botoesTextList) {
            JButton button = new JButton(buttonText);
            button.addActionListener(this);
            add(button);
        }
        this.display = display;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String buttonText = e.getActionCommand();

        if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")) {
            op = buttonText;
            ans = Integer.parseInt(display.getText());
            op_happening = true;
        } else if (buttonText.equals("C")) {
            displayText = "0";
            op = "";
            ans = 0;
        } else if (buttonText.equals("=")) {
            equalValue(op);
        } else {
            if (display.getText().equals("0") || op_happening) {
                displayText = buttonText;
                op_happening = false;
            } else {
                displayText = display.getText() + buttonText;
            }
            num = Integer.parseInt(displayText);
        }
        display.setDisplay(displayText);
    }

    void equalValue(String op){
        switch (op) {

                case "+" -> {
                    ans += num;
                    displayText = String.valueOf(ans);
                }
                case "-" -> {
                    ans -= num;
                    displayText = String.valueOf(ans);
                }
                case "*" -> {
                    ans *= num;
                    displayText = String.valueOf(ans);
                }
                case "/" -> {
                    if (!display.getText().equals("0")) {
                        ans /= num;
                        displayText = String.valueOf(ans);
                    } else {
                        displayText = "ERROR";
                    }
                }
            }

    }
}


