package org.exemple;

import org.example.BuscaHorario;
import org.example.HorarioAtendimento;
import org.example.HorarioServico;
import org.example.HorarioServicoImpl;

public class Main {
    public static void main(String[] args) {
        // Cria uma instância da implementação do serviço
        HorarioServico horarioService = new HorarioServicoImpl();

        // Cria uma instância da classe de busca
        BuscaHorario buscaHorario = new BuscaHorario(horarioService);

        // Usa o serviço para buscar informações
        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(1);

        // Exibe as informações do professor
        System.out.println("Nome do Professor: " + horario.getNomeDoProfessor());
        System.out.println("Horário de Atendimento: " + horario.getHorarioDeAtendimento());
        System.out.println("Período: " + horario.getPeriodo());
        System.out.println("Sala: " + horario.getSala());
        System.out.println("Prédio: " + horario.getPredio());
    }
}