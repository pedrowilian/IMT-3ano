package textfield;

import javax.swing.JFrame;

public class TextFieldFrameTest {
    public static void main(String[] args) {
        TextFieldFrame textFieldFrame = new TextFieldFrame();
        textFieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textFieldFrame.setSize(400, 300);
        textFieldFrame.setVisible(true);
    }
}
