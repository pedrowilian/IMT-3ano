package combobox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ComboBoxFrame extends JFrame
{
    private JComboBox imagesJComboBox;
    private JLabel label;
    private static final String[] names = {"bug1.gif", "bug2.gif", "buganim.gif", "travelbug.gif"};
    private Icon icons[] = {
        new ImageIcon(names[0]),
        new ImageIcon(names[1]),
        new ImageIcon(names[2]),
        new ImageIcon(names[3])
    };

    public ComboBoxFrame()
    {
        super("Testando JComboBox com Ícones");
        setLayout(new FlowLayout());
        imagesJComboBox = new JComboBox(names);
        imagesJComboBox.setMaximumRowCount(3);
        imagesJComboBox.addItemListener
        (new ItemListener()
          {
            public void itemStateChanged(ItemEvent event)
            {
                if (event.getStateChange() == ItemEvent.SELECTED)
                {
                    label.setIcon(icons[imagesJComboBox.getSelectedIndex()]);
                }
            }
          });
          add(imagesJComboBox);
            label = new JLabel(icons[0]);
            add(label);
    }


}