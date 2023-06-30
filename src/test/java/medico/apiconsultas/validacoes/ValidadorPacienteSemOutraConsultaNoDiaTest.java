package medico.apiconsultas.validacoes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
public class ValidadorPacienteSemOutraConsultaNoDiaTest {
    
    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ValidadorPacienteSemOutraConsultaNoDia validador;

    @Test
    @DisplayName("Teste agendar consulta paciente não possui consulta no mesmo dia")
    public void testAgendarConsultaPacientaNaoPossuiConsultaNoMesmoDia() {

        var especialidade = Especialidade.DERMATOLOGIA;

        LocalDateTime dataConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        when(consultaRepository.existsByPacienteIdAndDataBetween(any(Long.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(false);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(141), Long.valueOf(140), dataConsulta, especialidade);

        Assertions.assertDoesNotThrow(() -> validador.validar(dados));

    }

    @Test
    @DisplayName("Teste agendar consulta paciente possui consulta no mesmo dia")
    public void testAgendarConsultaPacientaPossuiConsultaNoMesmoDia() {

        var especialidade = Especialidade.DERMATOLOGIA;

        LocalDateTime dataConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        when(consultaRepository.existsByPacienteIdAndDataBetween(any(Long.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(141), Long.valueOf(140), dataConsulta, especialidade);

        Assertions.assertThrows(ValidacaoException.class, () -> validador.validar(dados));

    }
    
}
