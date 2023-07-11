package medico.apiconsultas.validacoes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import medico.apiconsultas.medico.*;
import medico.apiconsultas.ValidacaoException;
import medico.apiconsultas.consulta.DadosAgendamentoConsulta;
import java.util.stream.Stream;

public class ValidadorHorarioFuncionamentoClinicaTest {

    @ParameterizedTest
    @MethodSource("horariosDatasValidasConsultas")
    @DisplayName("Teste agendar consulta em dia/horario validos de funcionamento da clinica")
    public void testValidarConsultaHorarioFuncionamentoClinica(int hora, int minuto, DayOfWeek dia, Especialidade especialidade) {

        ValidadorHorarioFuncionamentoClinica validador = new ValidadorHorarioFuncionamentoClinica();

        LocalDateTime data = LocalDate.now().with(TemporalAdjusters.next(dia)).atTime(hora, minuto);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(845), Long.valueOf(445), data, especialidade);

        Assertions.assertDoesNotThrow(() -> validador.validar(dados));

    }

        private static Stream<Arguments> horariosDatasValidasConsultas() {
        return Stream.of(
                Arguments.of(7, 0, DayOfWeek.MONDAY, Especialidade.CARDIOLOGIA),
                Arguments.of(7, 1, DayOfWeek.TUESDAY, Especialidade.DERMATOLOGIA),
                Arguments.of(10, 0, DayOfWeek.FRIDAY, Especialidade.CARDIOLOGIA),
                Arguments.of(18, 58, DayOfWeek.WEDNESDAY, Especialidade.GINECOLOGIA),
                Arguments.of(18, 59, DayOfWeek.THURSDAY, Especialidade.ORTOPEDIA));
    }

    @ParameterizedTest
    @MethodSource("horariosDatasInvalidasConsultas")
    @DisplayName("Teste agendar consulta em dia/horario invalidos de funcionamento da clinica")
    public void testValidarConsultaHorarioInvalidoFuncionamentoClinica(int hora, int minuto, DayOfWeek dia, Especialidade especialidade) {

        ValidadorHorarioFuncionamentoClinica validador = new ValidadorHorarioFuncionamentoClinica();

        LocalDateTime data = LocalDate.now().with(TemporalAdjusters.next(dia)).atTime(hora, minuto);

        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(Long.valueOf(555), Long.valueOf(333), data, especialidade);

        Assertions.assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    
    }

        private static Stream<Arguments> horariosDatasInvalidasConsultas() {
        return Stream.of(
                Arguments.of(6, 59, DayOfWeek.MONDAY, Especialidade.CARDIOLOGIA),
                Arguments.of(5, 0, DayOfWeek.TUESDAY, Especialidade.DERMATOLOGIA),
                Arguments.of(10, 0, DayOfWeek.SUNDAY, Especialidade.CARDIOLOGIA),
                Arguments.of(20, 58, DayOfWeek.WEDNESDAY, Especialidade.GINECOLOGIA),
                Arguments.of(21, 0, DayOfWeek.SATURDAY, Especialidade.ORTOPEDIA));
    }

}
