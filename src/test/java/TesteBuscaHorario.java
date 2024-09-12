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
    public void testPredioCorreto_Sala1a5() {
        when(horarioServicoMock.buscaHorario(1)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"4\", \"predio\": [1]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(1);

        assertEquals(1, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_Sala6a10() {
        when(horarioServicoMock.buscaHorario(2)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"7\", \"predio\": [2]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(2);

        assertEquals(2, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_Sala11a15() {
        when(horarioServicoMock.buscaHorario(3)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"12\", \"predio\": [3]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(3);

        assertEquals(3, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_Sala16a20() {
        when(horarioServicoMock.buscaHorario(4)).thenReturn(
                "{\"nomeDoProfessor\": \"Prof. Teste\", \"horarioDeAtendimento\": \"08:00 - 10:00\", \"periodo\": \"matutino\", \"sala\": \"18\", \"predio\": [4]}"
        );

        HorarioAtendimento horario = buscaHorario.buscaHorarioProfessor(4);

        assertEquals(4, horario.getPredio());
    }

    @Test
    public void testPredioCorreto_Sala21a25() {
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
}