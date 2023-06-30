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
import medico.apiconsultas.medico.MedicoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorMedicoAtivoTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private ValidadorMedicoAtivo validador;

    @Test
    @DisplayName("Teste agendar consulta com medico ativo")
    public void testValidarConsultaMedicoAtivo() {

        var especialidade = Especialidade.CARDIOLOGIA;

        when(medicoRepository.findAtivoById(any(Long.class))).thenReturn(true);

        LocalDateTime dataConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(141), Long.valueOf(140), dataConsulta, especialidade);

        Assertions.assertDoesNotThrow(() -> validador.validar(dados));

    }

    @Test
    @DisplayName("Teste agendar consulta com medico inativo")
    public void testValidarConsultaMedicoInativo() {

        var especialidade = Especialidade.CARDIOLOGIA;

        when(medicoRepository.findAtivoById(any(Long.class))).thenReturn(false);

        LocalDateTime dataConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(141), Long.valueOf(141), dataConsulta, especialidade);

        Assertions.assertThrows(ValidacaoException.class, () -> validador.validar(dados));

    }
    
}
