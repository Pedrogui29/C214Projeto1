import org.example.BuscaHorario;
import org.example.HorarioAtendimento;
import org.example.HorarioServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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


}
