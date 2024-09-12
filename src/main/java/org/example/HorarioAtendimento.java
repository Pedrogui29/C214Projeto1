package org.example;

public class HorarioAtendimento {
    private  String nomeDoProfessor;
    private  String horarioDeAtendimento;
    private String periodo;
    private int sala;
    private int predio;

    public HorarioAtendimento(String nomeDoProfessor, String horarioDeAtendimento, String periodo, int sala, int predio) {
        this.nomeDoProfessor = nomeDoProfessor;
        this.horarioDeAtendimento = horarioDeAtendimento;
        this.periodo = periodo;
        this.sala = sala;
        this.predio = predio;
    }

    public String getNomeDoProfessor() {
        return nomeDoProfessor;
    }

    public String getHorarioDeAtendimento() {
        return horarioDeAtendimento;
    }

    public String getPeriodo() {
        return periodo;
    }

    public int getSala() {
        return sala;
    }

    public int getPredio() {
        return predio;
    }
}
