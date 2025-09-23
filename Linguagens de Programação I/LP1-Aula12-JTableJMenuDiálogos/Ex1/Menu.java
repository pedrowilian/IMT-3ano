package Ex1;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

class Menu extends JFrame {
     // Declaração dos menus e itens
     private JMenuBar menuBar;
     private JMenu arrayMenu, estatisticaMenu, cdiMenu, algesdMenu;
     private JMenuItem[] arrayItems, estatisticaItems, cdiItems, algesdItems, ordenacaoItems, buscaItems;
 
     // Variáveis auxiliares
     private int arraySize; // Tamanho do vetor
     double[] array;        // Vetor original
     double[] arraySort;    // Vetor auxiliar para ordenação
 
     // Variáveis para estatísticas
     double media, desvioPadrao, variancia, mediana, coeficienteAssimetria, coeficienteVariacao;

    // ====================================================================================== //
    // ======================================== MENU ======================================== //
    // ====================================================================================== //

    // Construtor da classe
    public Menu() {
        // Título da janela
        super("Menu");

        // Inicialização da barra de menu
        menuBar = new JMenuBar();

        // =============================================== //
        // ==================== VETOR ==================== //
        // =============================================== //

        // Criação do Menu Vetor e seus itens
        arrayMenu = new JMenu("Vetor");
        String[] arrayOptions = {"Dimensionar", "Digitar", "Apresentar"};
        arrayItems = new JMenuItem[arrayOptions.length];


        // Adiciona os itens ao Menu Vetor
        for (int i = 0; i < arrayOptions.length; i++) {
            arrayItems[i] = new JMenuItem(arrayOptions[i]);
            arrayMenu.add(arrayItems[i]);
        }
        menuBar.add(arrayMenu); // Adiciona menu à barra
        
        // ======================== //
        // ======= COMANDOS ======= //
        // ======================== //

        // 1- Tamanho do Vetor 
        arrayItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arraySize = Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho do vetor: "));
                JOptionPane.showMessageDialog(null, "Tamanho do vetor: " + arraySize);
            }
        });

        // 2- Digitar os valores que serão adcionados ao vetor 
        arrayItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                array = new double[arraySize];
                for (int i = 0; i < arraySize; i++) {
                    array[i] = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do vetor na posição " + i + ": "));
                }
                JOptionPane.showMessageDialog(null, "Vetor: " + Arrays.toString(array));
            }
        });

        // 3- Visualização do Vetor 
        arrayItems[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Vetor: " + Arrays.toString(array));
            }
        });

        // ===================================================== //
        // ==================== ESTATÍSTICA ==================== //
        // ===================================================== //

        // Criação do menu Estatística
        estatisticaMenu = new JMenu("Estatística");
        menuBar.add(estatisticaMenu);

        // Itens do menu Estatística
        String[] estatisticaOptions = {"Média", "Desvio Padrão", "Variância", "Mediana", "Coef. Assimetria", "Coef. Variação"};
        estatisticaItems = new JMenuItem[estatisticaOptions.length];

        for (int i = 0; i < estatisticaOptions.length; i++) {
            estatisticaItems[i] = new JMenuItem(estatisticaOptions[i]);
            estatisticaMenu.add(estatisticaItems[i]);
        }

        // ======================== //
        // ======= COMANDOS ======= //
        // ======================== //

        // 1- Média
        estatisticaItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaMedia();
                JOptionPane.showMessageDialog(null, "Média: " + media);
            }
        });

        // 2- Desvio Padrão
        estatisticaItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaDesvioPadrao();
                JOptionPane.showMessageDialog(null, "Desvio Padrão: " + desvioPadrao);
            }
        });

        // 3- Variância
        estatisticaItems[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaVariancia();
                JOptionPane.showMessageDialog(null, "Variância: " + variancia);
            }
        });

        // 4- Mediana
        estatisticaItems[3].addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calculaMediana();
                        JOptionPane.showMessageDialog(null, "Mediana: " + mediana);
                    }
                }
        );

        // 5- Coeficiente de Assimetria 
        estatisticaItems[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaCoeficienteAssimetria();
                JOptionPane.showMessageDialog(null, "Coeficiente de Assimetria: " + coeficienteAssimetria);
            }
        });

        // 6- Coeficiente de Variação
        estatisticaItems[5].addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calculaCoeficienteVariacao();
                        JOptionPane.showMessageDialog(null, "Coeficiente de Variação: " + coeficienteVariacao);
                    }
                }
        );

        // ================================================= //
        // ==================== CÁLCULO ==================== //
        // ================================================= //

        // Menu CDI (função e derivada)
        cdiMenu = new JMenu("CDI");

        menuBar.add(cdiMenu);

        String[] cdiOptions = {"Função", "Derivada"};

        cdiItems = new JMenuItem[cdiOptions.length];

        for (int i = 0; i < cdiItems.length; i++) {
            cdiItems[i] = new JMenuItem(cdiOptions[i]);
            cdiMenu.add(cdiItems[i]);
        }

        // ======================== //
        // ======= COMANDOS ======= //
        // ======================== //

        // 1- Função 
        cdiItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String function = "";

                for (int i = 0; i < arraySize - 1; i++) {
                    function += array[i] + "x^" + (arraySize - i - 1) + " + ";
                }
                function += array[arraySize - 1];

                JOptionPane.showMessageDialog(null, "Função: " + function);
            }
        });

        // 2- Derivada
        cdiItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String function = "";

                for (int i = 0; i < arraySize - 2; i++) {
                    function += (array[i] * (arraySize - i - 1)) + "x^" + (arraySize - i - 2) + " + ";
                }

                function += array[arraySize - 2];

                JOptionPane.showMessageDialog(null, "Derivada: " + function);
            }
        });

        // ==================================================== // 
        // ==================== ALGORITMOS ==================== //
        // ==================================================== //

        // Menu ALGESD (ordenação e busca)
        algesdMenu = new JMenu("Algesd");

        menuBar.add(algesdMenu);

        String[] algesdOptions = {"Ordenação", "Busca"};

        algesdItems = new JMenu[algesdOptions.length];

        for (int i = 0; i < algesdItems.length; i++) {
            algesdItems[i] = new JMenu(algesdOptions[i]);
            algesdMenu.add(algesdItems[i]);
        }

        // Itens de ordenação
        String[] ordenacaoOptions = {"Bubble Sort", "Insertion Sort", "Selection Sort", "Merge Sort", "Quick Sort"};

        ordenacaoItems = new JMenuItem[ordenacaoOptions.length];

        for (int i = 0; i < ordenacaoItems.length; i++) {
            ordenacaoItems[i] = new JMenuItem(ordenacaoOptions[i]);
            algesdItems[0].add(ordenacaoItems[i]);
        }

        // ================================== //
        // ======= COMANDOS ORDENAÇÃO ======= //
        // ================================== //

        // 1- BubbleSort
        ordenacaoItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bubbleSort();
                JOptionPane.showMessageDialog(null, "Vetor ordenado por bubbleSort: " + Arrays.toString(arraySort));
            }
        });

        // 2- InsertionSort
        ordenacaoItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertionSort();
                JOptionPane.showMessageDialog(null, "Vetor ordenado por insertionSort: " + Arrays.toString(arraySort));
            }
        });

        // 3- SelectionSort
        ordenacaoItems[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionSort();
                JOptionPane.showMessageDialog(null, "Vetor ordenado por selectionSort: " + Arrays.toString(arraySort));
            }
        });

        // 4- MergeSort
        ordenacaoItems[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                arraySort = array.clone();

                mergeSort(arraySort, 0, arraySize - 1);
                JOptionPane.showMessageDialog(null, "Vetor ordenado por mergeSort: " + Arrays.toString(arraySort));
            }
        });

        // 5- QuickSort
        ordenacaoItems[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arraySort = array.clone();
                quickSort(0, arraySize - 1);
                JOptionPane.showMessageDialog(null, "Vetor ordenado por quickSort: " + Arrays.toString(arraySort));
            }
        });

        // ============================== //
        // ======= COMANDOS BUSCA ======= //
        // ============================== //

        // Itens de busca
        String[] buscaOptions = {"Linear Iterativa", "Linear Recursiva", "Binária Iterativa", "Binária Recursiva"};

        buscaItems = new JMenuItem[buscaOptions.length];

        for (int i = 0; i < buscaItems.length; i++) {
            buscaItems[i] = new JMenuItem(buscaOptions[i]);
            algesdItems[1].add(buscaItems[i]);
        }

        // 1- Linear Iterativa
        buscaItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int key = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor a ser buscado: "));
                linearInterativeSearch(key);
            }
        });

        // 2- Linear Recursiva
        buscaItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int key = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor a ser buscado: "));
                linearRecursiveSearch(key, 0);
            }
        });

        // 3- Binária Iterativa
        buscaItems[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bubbleSort();
                int key = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor a ser buscado: "));
                binaryInterativeSearch(key);
            }
        });

        // 4- Binária Recursiva
        buscaItems[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bubbleSort();
                int key = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor a ser buscado: "));
                binaryRecursiveSearch(key, 0, arraySize - 1);
            }
        });

        // Configuração final da janela
        setJMenuBar(menuBar);
        setSize(400, 400);                  // Tamanho da janela
        setVisible(true);                              // Torna a janela visível
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Fecha ao clicar no X
        setLocationRelativeTo(null);                   // Centraliza na tela
    }

    // ========================================================================================= //
    // ======================================== FUNÇÕES ======================================== //
    // ========================================================================================= //

    // ===================================================== //
    // ==================== ESTATÍSTICA ==================== //
    // ===================================================== //

    // 1- Estatística
    private void calculaCoeficienteVariacao() {
        calculaDesvioPadrao();
        calculaMedia();
        coeficienteVariacao = (desvioPadrao / media) * 100;
    }

    // 2- Estatística
    public void calculaMedia() {
        media = 0;
        for (int i = 0; i < arraySize; i++) {
            media += array[i];
        }
        media /= arraySize;
    }

    // 3- Estatística
    public void calculaMediana() {
        double[] arrayCopy = array.clone();
        Arrays.sort(arrayCopy);
        if (arrayCopy.length % 2 == 0) {
            mediana = (arrayCopy[arrayCopy.length / 2] + arrayCopy[arrayCopy.length / 2 - 1]) / 2;
        } else {
            mediana = arrayCopy[arrayCopy.length / 2];
        }
    }

    // 4- Estatística
    public void calculaDesvioPadrao() {
        calculaMedia();
        desvioPadrao = 0;
        for (int i = 0; i < arraySize; i++) {
            desvioPadrao += Math.pow(array[i] - media, 2);
        }
        desvioPadrao /= arraySize;
        desvioPadrao = Math.sqrt(desvioPadrao);
    }

    // 5- Estatística
    public void calculaVariancia() {
        calculaMedia();
        variancia = 0;
        for (int i = 0; i < arraySize; i++) {
            variancia += Math.pow(array[i] - media, 2);
        }
        variancia /= arraySize;
    }

    // 6- Estatística
    public void calculaCoeficienteAssimetria() {
        calculaMedia();
        calculaMediana();
        calculaDesvioPadrao();
        coeficienteAssimetria = 3 * (media - mediana) / desvioPadrao;
    }

    // ==================================================== //
    // ==================== ALGORITMOS ==================== //
    // ==================================================== //
    
    // ===================== //
    // ===== Ordenação ===== //
    // ===================== //

    // 1- Ordenação
    public void bubbleSort() {
        arraySort = array.clone();
        double aux;
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize - 1; j++) {
                if (arraySort[j] > arraySort[j + 1]) {
                    aux = arraySort[j];
                    arraySort[j] = arraySort[j + 1];
                    arraySort[j + 1] = aux;
                }
            }
        }
    }

    // 2- Ordenação
    public void insertionSort() {
        arraySort = array.clone();
        double aux;
        for (int i = 1; i < arraySize; i++) {
            for (int j = i; j > 0; j--) {
                if (arraySort[j] < arraySort[j - 1]) {
                    aux = arraySort[j];
                    arraySort[j] = arraySort[j - 1];
                    arraySort[j - 1] = aux;
                }
            }
        }
    }

    // 3- Ordenação
    public void selectionSort() {
        arraySort = array.clone();
        double aux;
        int min;
        for (int i = 0; i < arraySize - 1; i++) {
            min = i;
            for (int j = i + 1; j < arraySize; j++) {
                if (arraySort[j] < arraySort[min]) {
                    min = j;
                }
            }
            aux = arraySort[i];
            arraySort[i] = arraySort[min];
            arraySort[min] = aux;
        }
    }

    // 4- Ordenação
    public void quickSort(int left, int right) {
        int i, j;
        double aux, pivot;
        i = left;
        j = right;
        pivot = array[(left + right) / 2];
        while (i <= j) {
            while (arraySort[i] < pivot && i < right) {
                i++;
            }
            while (arraySort[j] > pivot && j > left) {
                j--;
            }
            if (i <= j) {
                aux = arraySort[i];
                arraySort[i] = arraySort[j];
                arraySort[j] = aux;
                i++;
                j--;
            }
        }
        if (j > left) {
            quickSort(left, j);
        }
        if (i < right) {
            quickSort(i, right);
        }
    }

    // 5- Ordenação
    public void mergeSort(double[] arraySort, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(arraySort, start, middle);
            mergeSort(arraySort, middle + 1, end);
            merge(arraySort, start, middle, end);
        }
    }

    public void merge(double[] arr, int start, int middle, int end) {
        double[] helper = new double[arr.length];
        if (end + 1 - start >= 0) System.arraycopy(arr, start, helper,
                start, end + 1 - start);
        int helperLeft = start;
        int helperRight = middle + 1;
        int current = start;
        while (helperLeft <= middle && helperRight <= end) {
            if (helper[helperLeft] <= helper[helperRight]) {
                arr[current] = helper[helperLeft];
                helperLeft++;
            } 
            else {
                arr[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }
        int remaining = middle - helperLeft;
        if (remaining + 1 >= 0) System.arraycopy(helper, helperLeft,
                arr, current, remaining + 1);
    }

    // ===================== //
    // ======= Busca ======= //
    // ===================== //

    // 1- Busca
    public void linearInterativeSearch(double key ) {
        int i = 0;
        while (i < arraySize && arraySort[i] != key) {
            i++;
        }
        if (i < arraySize) {
            JOptionPane.showMessageDialog(null, "O elemento " + key);
        } 
        else {
            JOptionPane.showMessageDialog(null, "O elemento " + key + " não foi encontrado");
        }

    }

    // 2- Busca
    public void linearRecursiveSearch(double key, int i) {
        if (i < arraySize) {
            if (arraySort[i] == key) {
                JOptionPane.showMessageDialog(null, "O elemento " + key + " foi encontrado" + i);
            } 
            else {
                linearRecursiveSearch(key, i + 1);
            }
        } 
        else {
            
            JOptionPane.showMessageDialog(null, "O elemento " + key + " não foi encontrado");
        }
    }

    // 3- Busca 
    public void binaryInterativeSearch(double key) {
        int left = 0;
        int right = arraySize - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (arraySort[middle] == key) {
                JOptionPane.showMessageDialog(null, "O elemento " + key + " foi encontrado na posição " + middle);
                return;
            } 
            else if (arraySort[middle] < key) {
                left = middle + 1;
            } 
            else {
                right = middle - 1;
            }
        }
        JOptionPane.showMessageDialog(null, "O elemento " + key + " não foi encontrado");
    }

    // 4- Busca
    public void binaryRecursiveSearch(double key, int left, int right){
        int middle;
        if (left <= right) {
            middle = (left + right) / 2;
            if (arraySort[middle] == key) {
                JOptionPane.showMessageDialog(null, "O elemento " + key + " foi encontrado na posição " + middle);
                return;
            } else if (arraySort[middle] < key) {
                binaryRecursiveSearch(key, middle + 1, right);
            } else {
                binaryRecursiveSearch(key, left, middle - 1);
            }
        } 
        else {
            JOptionPane.showMessageDialog(null, "O elemento " + key + " não foi encontrado");
        }
    }
}


