package org.example;

import java.util.HashMap;
import java.util.Map;

public class HorarioServicoImpl extends HorarioServico {

    // Mapa para armazenar os dados dos professores
    private Map<Integer, String> professores = new HashMap<>();

    // Construtor para popular os dados iniciais dos professores
    public HorarioServicoImpl() {
        // Simulando dados de professores com IDs
        professores.put(1, "{\"nomeDoProfessor\": \"Prof. Yvo\", \"horarioDeAtendimento\": \"10:00 - 12:00\", \"periodo\": \"integral\", \"sala\": \"3\", \"predio\": [1]}");
        professores.put(2, "{\"nomeDoProfessor\": \"Prof. Renzo\", \"horarioDeAtendimento\": \"14:00 - 16:00\", \"periodo\": \"noturno\", \"sala\": \"7\", \"predio\": [2]}");
        professores.put(3, "{\"nomeDoProfessor\": \"Prof. Chris\", \"horarioDeAtendimento\": \"09:00 - 11:00\", \"periodo\": \"integral\", \"sala\": \"2\", \"predio\": [1]}");
        // Adicione mais professores conforme necessário
    }

    // Implementação do método para buscar o horário do professor
    @Override
    public String buscaHorario(int idProfessor) {
        // Busca o JSON correspondente ao professor com base no ID
        return professores.get(idProfessor);
    }

    // Implementação para verificar se o professor existe
    @Override
    public boolean professorExistente(int idProfessor) {
        return professores.containsKey(idProfessor);
    }
}