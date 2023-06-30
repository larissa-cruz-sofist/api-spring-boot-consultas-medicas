package medico.apiconsultas.validacoes;

import static org.mockito.ArgumentMatchers.any;
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
import medico.apiconsultas.consulta.DadosAgendamentoConsulta;
import medico.apiconsultas.medico.Especialidade;
import medico.apiconsultas.paciente.PacienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorPacienteAtivoTest {
    
    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private ValidadorPacienteAtivo validador;

    @Test
    @DisplayName("Teste agendar consulta com paciente ativo")
    public void testValidarConsultaPacienteAtivo() {

        var especialidade = Especialidade.CARDIOLOGIA;

        when(pacienteRepository.findAtivoById(any(Long.class))).thenReturn(true);

        LocalDateTime dataConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(122), Long.valueOf(123), dataConsulta, especialidade);

        Assertions.assertDoesNotThrow(() -> validador.validar(dados));

    }

    @Test
    @DisplayName("Teste agendar consulta com paciente inativo")
    public void testValidarConsultaPacienteInativo() {

        var especialidade = Especialidade.CARDIOLOGIA;

        when(pacienteRepository.findAtivoById(any(Long.class))).thenReturn(false);

        LocalDateTime dataConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(124), Long.valueOf(125), dataConsulta, especialidade);

        Assertions.assertThrows(ValidacaoException.class, () -> validador.validar(dados));

    }

}
