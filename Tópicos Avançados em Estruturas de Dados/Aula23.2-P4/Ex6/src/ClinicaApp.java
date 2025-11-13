import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Main no terminal

public class ClinicaApp {

    public static void main(String[] args) {
        Grafo agendaGrafo = new Grafo();
        Scanner scanner = new Scanner(System.in);

        String[] pacientes = {"P1", "P2", "P3", "P4", "P5"};
        String[] medicos = {"M1", "M2", "M3", "M4", "M5", "M6"};

        for (String pLabel : pacientes) {
            agendaGrafo.adicionarNo(pLabel);
        }
        for (String mLabel : medicos) {
            agendaGrafo.adicionarNo(mLabel);
        }

        System.out.println("--- Cadastro de Preferências de Consulta ---");
        System.out.println("Instruções: Digite os médicos separados por vírgula (ex: M1, M3)");

        for (String pLabel : pacientes) {
            System.out.print("Paciente " + pLabel + ": ");
            String input = scanner.nextLine(); 

            if (input == null || input.trim().isEmpty()) {
                continue;
            }

            String[] medicosEscolhidos = input.split(",");
            for (String mLabel : medicosEscolhidos) {
                String mLabelLimpo = mLabel.trim().toUpperCase(); 

                No medicoNo = agendaGrafo.getNo(mLabelLimpo);
                if (medicoNo != null) {
                    agendaGrafo.adicionarAresta(pLabel, mLabelLimpo);
                } else {
                    System.out.println("(Atenção: Médico '" + mLabelLimpo + "' não encontrado. Ignorando.)");
                }
            }
        }
        scanner.close(); 
        System.out.println("\nGerando Agenda...");

        System.out.print("\n---------------------------------------------------");
        System.out.println("\nClínica Tabajara");
        System.out.println("Agenda de Alocação de Consultas");
        System.out.println("\tDia: Próxima 2a feira, 9h00min");

        List<String> listaPacientesPorMedico = new ArrayList<>();
        for (String mLabel : medicos) {
            No noMedico = agendaGrafo.getNo(mLabel);
            List<No> pacientesDoMedico = agendaGrafo.getPacientesPorMedico(noMedico);

            String pacientesStr;
            if (pacientesDoMedico.isEmpty()) {
                pacientesStr = "-"; 
            } else {
                pacientesStr = pacientesDoMedico.stream()
                                         .map(No::getLabel) 
                                         .collect(Collectors.joining(", ")); 
            }
            listaPacientesPorMedico.add(pacientesStr);
        }
        
        System.out.println(); 
        
        List<Integer> columnWidths = new ArrayList<>();
        for (int i = 0; i < medicos.length; i++) {
            int medWidth = medicos[i].length();
            int patWidth = listaPacientesPorMedico.get(i).length();
            int colWidth = Math.max(medWidth, patWidth) + 2; 
            columnWidths.add(colWidth);
        }
        
        System.out.printf("%-11s", "Médicos:");
        for (int i = 0; i < medicos.length; i++) {
            String text = medicos[i];
            int colWidth = columnWidths.get(i);
            
            int paddingTotal = colWidth - text.length();
            if (paddingTotal < 0) paddingTotal = 0;
            
            int paddingLeft = paddingTotal / 2;
            int paddingRight = paddingTotal - paddingLeft;
            
            System.out.printf("%" + paddingLeft + "s%s%" + paddingRight + "s", "", text, "");
        }
        System.out.println();

        System.out.printf("%-11s", "Pacientes:");
        for (int i = 0; i < listaPacientesPorMedico.size(); i++) {
            String text = listaPacientesPorMedico.get(i);
            int colWidth = columnWidths.get(i);

            int paddingTotal = colWidth - text.length();
            if (paddingTotal < 0) paddingTotal = 0; 

            int paddingLeft = paddingTotal / 2;
            int paddingRight = paddingTotal - paddingLeft;
            System.out.printf("%" + paddingLeft + "s%s%" + paddingRight + "s", "", text, "");
        }
        System.out.println("\n---------------------------------------------------");
    }
    
}