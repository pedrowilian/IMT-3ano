package Ex5;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class Display extends JTextField {

    private String display = "0";
    Display() {
        super("Calculadora");
        Font font;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(""));
            font = font.deriveFont(Font.BOLD, 24);
        } catch (FontFormatException | IOException e) {
            font = new Font("Arial", Font.BOLD, 24);
        }

         setFont(font);




        setBackground(new Color(255, 255, 255));

        setHorizontalAlignment(JTextField.RIGHT);

        setEditable(false);
        setDisplay(display);

        setVisible(true);
    }

    public void setDisplay(String displayText) {
        this.display = displayText;
        this.setText(display);
    }



}
