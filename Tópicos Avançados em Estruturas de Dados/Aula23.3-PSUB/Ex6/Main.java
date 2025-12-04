import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Grafo grafo = new Grafo();

        Map<String, Vertice> mapaVertices = new HashMap<>();
        
        List<Vertice> pacientesList = new ArrayList<>();
        List<Vertice> medicosList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String nome = "P" + i;
            Vertice p = new Vertice(nome);
            grafo.addVertice(p);
            mapaVertices.put(nome, p);
            pacientesList.add(p);
        }

        for (int j = 1; j <= 6; j++) {
            String nome = "M" + j;
            Vertice m = new Vertice(nome);
            grafo.addVertice(m);
            mapaVertices.put(nome, m);
            medicosList.add(m);
        }
        System.out.println("Sistema da Clinica Tabajara");
        System.out.println("Digite um medico ou medicos do tipo (M1,M2,M3,M4,M5,M6),\nPara agendar sua consulta Paciente!");
        for (int i = 1; i <= 5; i++) {
            String pacienteId = "P" + i;
            Vertice paciente = mapaVertices.get(pacienteId);

            System.out.print(pacienteId + ": ");

            String linha = sc.nextLine().trim().toUpperCase();

            String[] tokens = linha.split(",\\s*");

            for (String token : tokens) {
                Vertice medico = mapaVertices.get(token);

                if (medico == null || !token.matches("M[1-6]")) {
                    if (!token.isEmpty()) {
                        System.out.println("Digite da forma Valida e correta! Saindo... Tente novamente !");
                        return;
                    }
                    continue;
                }

                Aresta aPM = new Aresta(paciente, medico);
                paciente.addAresta(aPM);

                Aresta aMP = new Aresta(medico, paciente);
                medico.addAresta(aMP);
            }
        }
        
        
        Emparelhamento resolvedor = new Emparelhamento(grafo, pacientesList, medicosList);
        List<String[]> emparelhamentos = resolvedor.calcularEmparelhamentoMaximo();

        int totalPacientes = pacientesList.size();
        int agendamentosFeitos = emparelhamentos.size();
        
        if (agendamentosFeitos < totalPacientes) {
            System.out.println("NAO HA EMPARELHAMENTO POSSIVEL");
            if (agendamentosFeitos == 0) {
                 System.out.println("Nenhum agendamento pôde ser realizado, verifique as preferências.");
            } else {
                 System.out.println("Apenas " + agendamentosFeitos + " de " + totalPacientes + " pacientes foram agendados, Erro no sistema!\n");
            }
        } else {
            System.out.println();
            System.out.println("Clínica Tabajara");
            System.out.println("Agenda de Alocação de Consultas");
            System.out.println("  Dia:   Próxima 2ª feira, 9h00min");

            Map<String, String> tabela = new HashMap<>();
            for (Vertice m : medicosList) {
                if (m.getEmparelhadoCom() != null) {
                    tabela.put(m.getDado(), m.getEmparelhadoCom().getDado());
                } else {
                    tabela.put(m.getDado(), "-");
                }
            }

            System.out.print("Médicos:   ");
            for (Vertice m : medicosList) {
                System.out.printf("%-5s ", m.getDado());
            }
            System.out.println();

            System.out.print("Pacientes: ");
            for (Vertice m : medicosList) {
                System.out.printf("%-5s ", tabela.get(m.getDado()));
            }
            System.out.println("\n");
        }

        sc.close();
    }
}
