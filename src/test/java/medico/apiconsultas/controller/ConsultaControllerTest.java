package medico.apiconsultas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import medico.apiconsultas.consulta.*;
import medico.apiconsultas.medico.Especialidade;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

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
	
    @MockBean
    private AgendaDeConsultas agendaDeConsultas;
	
  	@Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes da consulta estao invalidas")
    @WithMockUser
    void agendarConsultaCenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
  	
    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes da consulta estao validas - cadastrar consulta")
    @WithMockUser
    void agendarConsultaCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data, true);
        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new DadosAgendamentoConsulta(2l, 5l, data, especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
    
    @Test
    @Disabled("Teste ignorado para pipline")
    @DisplayName("Deveria devolver codigo http 204 quando informacoes estao validas - excluir consulta")
    @WithMockUser
    void excluirConsultaCenario1() throws Exception {
        var response = mvc.perform(delete("/consultas/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());

    }
    
    @Test
    @DisplayName("Deveria devolver codigo http 404 quando consulta nao existe - excluir consulta")
    @WithMockUser
    void excluirConsultaCenario2() throws Exception {
        var response = mvc.perform(delete("/consultas/1000000000"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }
    
}
