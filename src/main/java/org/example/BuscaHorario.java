package org.example;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class BuscaHorario {
    private final HorarioServico horarioServico;

    public BuscaHorario(HorarioServico service) {
        this.horarioServico = service;
    }

    public HorarioAtendimento buscaHorarioProfessor(int idProfessor) {
        // Busca o horário do professor a partir do id
        String horarioJson = horarioServico.buscaHorario(idProfessor);

        // Verifica se o JSON retornado é nulo e retrona null se não tiver nenhum dado
        if (horarioJson == null) {
            return null;
        }

        // Faz o parsing do JSON recebido
        JsonObject jsonObject = JsonParser.parseString(horarioJson).getAsJsonObject();

        // Verifica se os campos obrigatórios do json
        if (!jsonObject.has("nomeDoProfessor")) {
            throw new CampoObrigatorioAusenteException("Campo 'Nome do Professor' está ausente no JSON.");
        }
        if (!jsonObject.has("horarioDeAtendimento")) {
            throw new CampoObrigatorioAusenteException("Campo 'Horario de Atendimento' está ausente no JSON.");
        }
        if (!jsonObject.has("periodo")) {
            throw new CampoObrigatorioAusenteException("Campo 'Periodo' está ausente no JSON.");
        }
        if (!jsonObject.has("sala")) {
            throw new CampoObrigatorioAusenteException("Campo 'Sala' está ausente no JSON.");
        }

        // Normaliza os valores removendo espacos extras do inicio e do final
        String nomeDoProfessor = jsonObject.get("nomeDoProfessor").getAsString().trim();
        String horarioDeAtendimento = jsonObject.get("horarioDeAtendimento").getAsString().trim();
        String periodo = jsonObject.get("periodo").getAsString().trim();

        int sala;
        try {
            // Converte do campo sala para int
            sala = jsonObject.get("sala").getAsInt();
        } catch (NumberFormatException e) {
            // Lanca uma exception personalizada se sala for invalido
            throw new CampoObrigatorioAusenteException("O valor do campo 'Sala' é inválido: deve ser um número.");
        }

        // Identifica o prédio com base no número da sala
        int predio = determinaPredio(sala);

        return new HorarioAtendimento(
                nomeDoProfessor,
                horarioDeAtendimento,
                periodo,
                sala,
                predio
        );
    }

    private int determinaPredio(int sala) {
        if (sala >= 1 && sala <= 5) {
            return 1;
        } else if (sala >= 6 && sala <= 10) {
            return 2;
        } else if (sala >=11 && sala <=15){
            return 3;
        }else if (sala >= 16 && sala <= 20) {
            return 4;
        } else if(sala >= 21 && sala <= 25) {
            return 6;
        }
        else{
            return 0;
        }
    }

    public boolean verificaProfessorExistente(int idProfessor) {
        return horarioServico.professorExistente(idProfessor);
    }
}
