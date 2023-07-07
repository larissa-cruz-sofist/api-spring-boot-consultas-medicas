package medico.apiconsultas.validacoes;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import medico.apiconsultas.medico.*;
import medico.apiconsultas.ValidacaoException;
import medico.apiconsultas.consulta.DadosAgendamentoConsulta;


public class ValidadorHorarioAntecedenciaTest {

    @Test
    @DisplayName("Teste agendar consulta com antecedencia suficiente = +30min")
    public void testValidarConsultaComAntecedenciaSuficiente() {

        ValidadorHorarioAntecedencia validador = new ValidadorHorarioAntecedencia();

        LocalDateTime dataConsulta = LocalDateTime.now().plusMinutes(31);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(145), Long.valueOf(140), dataConsulta, Especialidade.CARDIOLOGIA);

        Assertions.assertDoesNotThrow(() -> validador.validar(dados));

    }

    @Test 
    @DisplayName("Teste agendar consulta sem antecedencia suficiente = -30min")
    public void testValidarConsultaSemAntecedenciaSuficiente() {

        ValidadorHorarioAntecedencia validador = new ValidadorHorarioAntecedencia();

        LocalDateTime dataConsulta = LocalDateTime.now().plusMinutes(29);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(111), Long.valueOf(222), dataConsulta, Especialidade.CARDIOLOGIA);

        Assertions.assertThrows(ValidacaoException.class, () -> validador.validar(dados));

    }
}
