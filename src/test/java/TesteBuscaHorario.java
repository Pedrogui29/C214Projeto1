import org.example.BuscaHorario;
import org.example.HorarioAtendimento;
import org.example.HorarioServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class TesteBuscaHorario {

    private HorarioServico horarioServicoMock;
    private BuscaHorario buscaHorario;

    @BeforeEach
    public void setup() {
        // Cria o mock do serviço
        horarioServicoMock = mock(HorarioServico.class);

        // Inicializa a classe BuscaHorario com o mock
        buscaHorario = new BuscaHorario(horarioServicoMock);
    }

    @Test
    public void testBuscaHorarioProfessor_Sucesso() {
        // Dado que o serviço retorna um JSON válido para o professor de ID 1
        when(horarioServicoMock.buscaHorario(1)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. João\", \"horarioDeAtendimento\": \"10:00 - 12:00\", \"periodo\": \"integral\", \"sala\": \"3\", \"predio\": [1]}"
        );

        // Quando buscamos o professor de ID 1
        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(1);

        // Então o nome do professor deve ser "Prof. João"
        assertEquals("Prof. João", horario.getNomeDoProfessor());
        assertEquals("10:00 - 12:00", horario.getHorarioDeAtendimento());
        assertEquals("integral", horario.getPeriodo());
        assertEquals(3, horario.getSala());
        assertEquals(1, horario.getPredio());
    }

    @Test
    public void testBuscaHorarioProfessor_FalhaProfessorNaoEncontrado() {
        // Dado que o serviço não retorna nada para o professor de ID 99
        when(horarioServicoMock.buscaHorario(99)).thenReturn(null);

        // Quando buscamos o professor de ID 99
        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(99);

        // Então o resultado deve ser nulo
        assertNull(horario);
    }

    @Test
    public void testPredioCorreto_Sala1a5_Sucesso() {
        when(horarioServicoMock.buscaHorario(1)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"4\", \"predio\": [1]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(1);

        assertEquals(1, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_Sala6a10_Sucesso() {
        when(horarioServicoMock.buscaHorario(2)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"7\", \"predio\": [2]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(2);

        assertEquals(2, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_Sala11a15_Sucesso() {
        when(horarioServicoMock.buscaHorario(3)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"12\", \"predio\": [3]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(3);

        assertEquals(3, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_Sala16a20_Sucesso() {
        when(horarioServicoMock.buscaHorario(4)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"18\", \"predio\": [4]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(4);

        assertEquals(4, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_Sala21a25_Sucesso() {
        when(horarioServicoMock.buscaHorario(5)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"22\", \"predio\": [6]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(5);

        assertEquals(6, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_SalaForaIntervalo() {
        when(horarioServicoMock.buscaHorario(6)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"30\", \"predio\": [0]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(6);

        assertEquals(0, horario.getPredio());
    }

    @Test
    public void testMockBuscaProfessor_Sucesso() {
        // Dado que o serviço retorna um JSON válido para o professor de ID 7
        when(horarioServicoMock.buscaHorario(7)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Lucas\", \"horarioDeAtendimento\": \"19:00 - 21:00\", \"periodo\": \"noturno\", \"sala\": \"6\"}"
        );

        // Quando buscamos o professor de ID 7
        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(7);

        // Então o nome do professor deve ser "Prof. Lucas"
        assertEquals("Prof. Lucas", horario.getNomeDoProfessor());
        assertEquals("19:00 - 21:00", horario.getHorarioDeAtendimento());
        assertEquals("noturno", horario.getPeriodo());
        assertEquals(6, horario.getSala());
        assertEquals(2, horario.getPredio());
    }

    @Test
    public void testBuscaHorarioProfessor_JSONComCamposAdicionais() {
        when(horarioServicoMock.buscaHorario(10)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"8\", \"predio\": [2], \"campoAdicional\": \"extra\"}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(10);

        // Teste para verificar se o método lida com JSON que contém campos adicionais não esperados
        assertEquals("Prof. Teste", horario.getNomeDoProfessor());
        assertEquals(2, horario.getPredio());
    }

    @Test
    public void testMenorSala_Sucesso() {
        when(horarioServicoMock.buscaHorario(8)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Fred\", \"horarioDeAtendimento\": \"10:00 - 11:30\", \"periodo\": \"matutino\", \"sala\": \"1\", \"predio\": [1]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(8);

        // Teste para verificar se pegar a menor sala funciona corretamente
        assertEquals(1, horario.getSala());
        assertEquals(1, horario.getPredio());
    }

    @Test
    public void testMaiorSala_Sucesso() {
        when(horarioServicoMock.buscaHorario(9)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Carlos\", \"horarioDeAtendimento\": \"15:00 - 17:00\", \"periodo\": \"integral\", \"sala\": \"25\"}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(9);

        // Teste para verificar se pegar a maior sala funciona corretamente
        assertEquals(25, horario.getSala());
        assertEquals(6, horario.getPredio());
    }
}