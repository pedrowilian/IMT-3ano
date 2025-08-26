package jtable;
import javax.swing.*;
import java.awt.*;

public class PhoneBook extends JFrame{
    JPanel background;
    JTable table;
    JScrollPane bar;
    Object[][] data = {
            {"2008001","John Doe", "123-456-7890"},
            {"2008002","Jane Smith", "987-654-3210"},
            {"2008003","Alice Johnson", "555-123-4567"},
            {"2008004","Bob Brown", "444-987-6543"}
    };    

    String [] columns = {"ID", "Nome", "Telefone"};

    public PhoneBook()
    {
        super("Contatos");
    }

    public void makeWindow()
    {
        background = new JPanel();
        background.setLayout(new GridLayout(1,1));
        table = new JTable(data, columns);
        bar = new JScrollPane(table);
        background.add(bar);
        getContentPane().add(background);
        setSize(400, 300);
        setVisible(true);
    }
}
