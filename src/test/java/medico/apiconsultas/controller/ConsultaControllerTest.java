package medico.apiconsultas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

import medico.apiconsultas.consulta.*;
import medico.apiconsultas.medico.*;
import medico.apiconsultas.paciente.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import medico.apiconsultas.controllers.ConsultaController;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

        @Autowired
        private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

        @InjectMocks
        private ConsultaController controller;

        @Mock
        private ConsultaRepository consultaRepository;

        @Mock
        private MedicoRepository medicoRepository;

        @Mock 
        private PacienteRepository pacienteRepository;

        @MockBean
        private AgendaDeConsultas agendaDeConsultas;

        @Test
        @DisplayName("Deveria devolver codigo http 400 quando informacoes da consulta estao invalidas")
        @WithMockUser
        void deveriaRetornarStatus400QuandoAgendarConsultaSemInformacoesBody() throws Exception {
                var response = mvc.perform(post("/consultas"))
                                .andReturn().getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("Deveria devolver codigo http 200 quando informacoes da consulta estao validas - cadastrar consulta")
        @WithMockUser
        void deveriaRetornarStatus200QuandoAgendarConsultaComInformacoesValidas() throws Exception {
                var data = LocalDateTime.now().plusHours(1);
                var especialidade = Especialidade.CARDIOLOGIA;

                var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data, true);
                when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

                var response = mvc
                                .perform(
                                                post("/consultas")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(dadosAgendamentoConsultaJson.write(
                                                                                new DadosAgendamentoConsulta(2l, 5l,
                                                                                                data, especialidade))
                                                                                .getJson()))
                                .andReturn().getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

                var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                                dadosDetalhamento).getJson();

                assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        }

        @ParameterizedTest
        @DisplayName("Deveria devolver codigo http 204 quando informacoes estao validas - excluir consulta")
        @MethodSource("argumentoAtivo")
        @WithMockUser
        void deveriaRetornarStatus204QuandoExcluirConsultaComInformacoesValidas(boolean ativo) throws Exception {

                Medico medico = Mockito.mock(Medico.class);

                Paciente paciente = Mockito.mock(Paciente.class);

                LocalDateTime data = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

                Consulta consulta = new Consulta(Long.valueOf(123), medico, paciente, data, ativo);
                when(consultaRepository.getReferenceById(any(Long.class))).thenReturn(consulta);

                ResponseEntity<Void> response = controller.excluir(consulta.getId());

                assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
                assertThat(response.getBody()).isNull();

                verify(consultaRepository).getReferenceById(any(Long.class));

        }

        	private static Stream<Arguments> argumentoAtivo() {
		return Stream.of(
				Arguments.of(true),
				Arguments.of(false));

	}

        @Test
        @DisplayName("Deveria devolver codigo http 404 quando consulta nao existe - excluir consulta")
        @WithMockUser
        void deveriaRetornarStatus404QuandoExcluirConsultaNaoExistente() throws Exception {
                var response = mvc.perform(delete("/consultas/1000000000"))
                                .andReturn().getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        }

}
