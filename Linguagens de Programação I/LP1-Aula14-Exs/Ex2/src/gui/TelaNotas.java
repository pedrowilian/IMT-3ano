package gui;

import dao.NotasDAO;
import model.Nota;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class TelaNotas extends JFrame {
    private String usuario;
    private NotasDAO notasDAO;
    private JTable tabela;
    private NotasTableModel modelo;

    public TelaNotas(String usuario) {
        super("Notas e Faltas - " + usuario);
        this.usuario = usuario;
        this.notasDAO = new NotasDAO();

        List<Nota> notas = notasDAO.getNotasDoUsuario(usuario);

        modelo = new NotasTableModel(notas);
        tabela = new JTable(modelo);

        tabela.setDefaultEditor(Float.class, new DefaultCellEditor(new JTextField()));
        tabela.setDefaultEditor(Integer.class, new DefaultCellEditor(new JTextField()));

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        // Painel de botões embaixo
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar Disciplina");
        JButton btnRemover = new JButton("Remover Disciplina");
        JButton btnSalvar = new JButton("Salvar Alterações");
        JButton btnVoltar = new JButton("Voltar");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnVoltar);

        add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões

        btnAdicionar.addActionListener(e -> {
            JTextField tfDisciplina = new JTextField();
            JTextField tfNota = new JTextField();
            JTextField tfFaltas = new JTextField();

            Object[] message = {
                "Disciplina:", tfDisciplina,
                "Nota (ex: 7.5):", tfNota,
                "Faltas (inteiro):", tfFaltas
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Adicionar Disciplina", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String disc = tfDisciplina.getText().trim();
                    float nota = Float.parseFloat(tfNota.getText().trim());
                    int faltas = Integer.parseInt(tfFaltas.getText().trim());

                    if (disc.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Disciplina não pode ser vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Nota novaNota = new Nota(0, usuario, disc, nota, faltas, null);
                    boolean inseriu = notasDAO.inserirNota(novaNota);
                    if (inseriu) {
                        // Atualiza o objeto nota com data de última alteração do banco
                        // Assumindo que inserirNota atualiza novaNota com essa data (conforme sugerido)
                        modelo.adicionarNota(novaNota);
                        JOptionPane.showMessageDialog(this, "Disciplina adicionada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao adicionar disciplina!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Nota ou faltas inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRemover.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma disciplina para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Nota n = modelo.getNota(linha);
            int resp = JOptionPane.showConfirmDialog(this, "Remover disciplina " + n.getDisciplina() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                boolean removeu = notasDAO.removerNota(n.getId());
                if (removeu) {
                    modelo.removerNota(linha);
                    JOptionPane.showMessageDialog(this, "Disciplina removida!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao remover disciplina!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSalvar.addActionListener(e -> {
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Nota n = modelo.getNota(i);
                boolean atualizado = notasDAO.atualizarNota(n);
                if (!atualizado) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar nota da disciplina: " + n.getDisciplina());
                    return;
                }
                // Atualiza o objeto com a data nova (assumindo método atualizarNota faz isso)
            }
            JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
            modelo.fireTableDataChanged();
        });

        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaLogin().setVisible(true);
        });

        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Classe interna modelo de tabela
    private static class NotasTableModel extends AbstractTableModel {

        private final String[] colunas = {"Disciplina", "Nota", "Faltas", "Última Alteração"};
        private java.util.List<Nota> notas;

        public NotasTableModel(java.util.List<Nota> notas) {
            this.notas = notas;
        }

        @Override
        public int getRowCount() {
            return notas.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch(columnIndex) {
                case 1: return Float.class;
                case 2: return Integer.class;
                case 3: return String.class;
                default: return String.class;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 1 || columnIndex == 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Nota n = notas.get(rowIndex);
            switch (columnIndex) {
                case 0: return n.getDisciplina();
                case 1: return n.getNota();
                case 2: return n.getFaltas();
                case 3:
                    if (n.getUltimaAlteracao() != null) {
                        return n.getUltimaAlteracao().toString();
                    } else {
                        return "N/A";
                    }
                default: return null;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Nota n = notas.get(rowIndex);
            try {
                switch (columnIndex) {
                    case 1: // Nota
                        if (aValue instanceof Float) {
                            n.setNota((Float) aValue);
                        } else if (aValue instanceof String) {
                            n.setNota(Float.parseFloat((String) aValue));
                        }
                        break;
                    case 2: // Faltas
                        if (aValue instanceof Integer) {
                            n.setFaltas((Integer) aValue);
                        } else if (aValue instanceof String) {
                            n.setFaltas(Integer.parseInt((String) aValue));
                        }
                        break;
                }
            } catch (NumberFormatException e) {
                // pode mostrar aviso se quiser
            }
        }

        public Nota getNota(int linha) {
            return notas.get(linha);
        }

        public void adicionarNota(Nota n) {
            notas.add(n);
            fireTableRowsInserted(notas.size() - 1, notas.size() - 1);
        }

        public void removerNota(int linha) {
            notas.remove(linha);
            fireTableRowsDeleted(linha, linha);
        }
    }
}
