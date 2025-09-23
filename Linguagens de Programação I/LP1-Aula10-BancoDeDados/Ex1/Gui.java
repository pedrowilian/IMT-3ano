package Ex1;

import javax.swing.*;

public class Gui {
    private final String sOptions[];

    public Gui(String sG[])
    {   sOptions = sG;
    }

    public  int getOption()
    {   return  JOptionPane.showOptionDialog
                (   null,
                    "Digite a opção desejada:",
                    "Conexão do Java com o MySQL",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    sOptions,
                    sOptions[sOptions.length-1]
                );
    }

}
