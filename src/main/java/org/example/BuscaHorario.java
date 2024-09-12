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

        // Identifica o prédio com base no número da sala
        int sala = jsonObject.get("sala").getAsInt();
        int predio = determinaPredio(sala);

        return new HorarioAtendimento(
                jsonObject.get("nomeDoProfessor").getAsString(),
                jsonObject.get("horarioDeAtendimento").getAsString(),
                jsonObject.get("periodo").getAsString(),
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
