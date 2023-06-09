package medico.apiconsultas.validacoes;

import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import medico.apiconsultas.ValidacaoException;
import medico.apiconsultas.consulta.ConsultaRepository;
import medico.apiconsultas.consulta.DadosAgendamentoConsulta;
import medico.apiconsultas.medico.Especialidade;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorMedicoComOutraConsultaNoMesmoHorarioTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ValidadorMedicoComOutraConsultaNoMesmoHorario validador;

    @Test
    @DisplayName("Teste agendar consulta medico não possui outra consulta no mesmo horario")
    public void testValidarConsultaMedicoNaoPossuiOutraConsultaMesmoHorario() {

        var especialidade = Especialidade.DERMATOLOGIA;

        LocalDateTime dataConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        when(consultaRepository.existsByMedicoIdAndData(Long.valueOf(141), dataConsulta)).thenReturn(false);
            
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(141), Long.valueOf(140), dataConsulta, especialidade);

        Assertions.assertDoesNotThrow(() -> validador.validar(dados));

    }

    @Test
    @DisplayName("Teste agendar consulta medico possui outra consulta no mesmo horario")
    public void testValidarConsultaMedicoPossuiOutraConsultaMesmoHorario() {

        var especialidade = Especialidade.DERMATOLOGIA;

        LocalDateTime dataConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)).atTime(10, 0);

        when(consultaRepository.existsByMedicoIdAndData(Long.valueOf(145), dataConsulta)).thenReturn(true);
            
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(145), Long.valueOf(146), dataConsulta, especialidade);

        Assertions.assertThrows(ValidacaoException.class, () -> validador.validar(dados));

    }
    
}
