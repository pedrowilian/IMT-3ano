// COMENTAR

package wordgraphle;

import javax.swing.SwingUtilities;
import wordgraphle.ui.WordGraphleFrame;

public class WordGraphleApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordGraphleFrame frame = new WordGraphleFrame(5, 6);
            frame.setVisible(true);
        });
    }
}
