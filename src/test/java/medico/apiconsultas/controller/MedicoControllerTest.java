package medico.apiconsultas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import medico.apiconsultas.controllers.MedicoController;
import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;
import medico.apiconsultas.medico.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Mock
	private MedicoRepository repository;
	
	@InjectMocks
	private MedicoController controller;

	@Test
	@DisplayName("Deveria devolver codigo http 400 quando informacoes do medico estao invalidas")
	@WithMockUser
	void cadastrarMedicoCenario1() throws Exception {
		var response = mvc.perform(post("/medicos"))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	@DisplayName("Deveria devolver codigo http 200 quando informacoes do medico estao validas - cadastrar medico")
	@WithMockUser
	void cadastrarMedicoCenario2() throws Exception {
		
		var especialidade = Especialidade.CARDIOLOGIA;
		
		DadosCadastroMedico dadosCadastroMedico = new DadosCadastroMedico("Marcos", "marcoslinhares@vold.med", "892083001", "801208", especialidade, new DadosEndereco("rua alegre", "santa paula", "12345412", "Sao Paulo", "SP", "bloco A", "200"));
		
		Medico medicoSalvo = new Medico(dadosCadastroMedico);
		when(repository.save(any(Medico.class))).thenReturn(medicoSalvo);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/");
	    ResponseEntity<DadosDetalhamentoMedico> response = controller.cadastrar(dadosCadastroMedico, uriBuilder);
		
	    verify(repository).save(any(Medico.class));
	     
	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
	    
	    assertThat(response.getBody())
	    .usingRecursiveComparison()
	    .ignoringFields("id")
	    .isEqualTo(new DadosDetalhamentoMedico(medicoSalvo));

	     
	}
	
    @ParameterizedTest
    @DisplayName("Deveria devolver codigo http 204 quando informacoes estao validas - excluir medico")
	@MethodSource("argumentoAtivo")
    @WithMockUser
    void excluirMedicoCenario1(boolean ativo) throws Exception {

		var endereco = new Endereco("rua andreas", "sao jose", "19999977", "2288", "bloco A", "Bahia", "BA");
		var especialidade = Especialidade.ORTOPEDIA;

		Medico medico = new Medico(Long.valueOf(123), "Carlos", "carlos@voll.med", "965412298", "801118", especialidade, endereco, ativo);
		when(repository.getReferenceById(any(Long.class))).thenReturn(medico);

		ResponseEntity<Void> response = controller.excluir(medico.getId());

        assertEquals(ResponseEntity.noContent().build(), response);

    }

	   private static Stream<Arguments> argumentoAtivo() {
        return Stream.of(
                Arguments.of(true),
                Arguments.of(false));

}
    
    @Test
    @DisplayName("Deveria devolver codigo http 404 quando medico nao existe - excluir medico")
    @WithMockUser
    void excluirMedicoCenario2() throws Exception {
        var response = mvc.perform(delete("/medicos/1000000000"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }
    
	@Test
	@DisplayName("Deveria devolver codigo http 200 quando informacoes do medico estao validas - alterar medico")
	@WithMockUser
	void alterarMedicoCenario1() throws Exception {
		
		Medico medicoSimulado = Mockito.mock(Medico.class);
		when(repository.getReferenceById(any(Long.class))).thenReturn(medicoSimulado);
			
	    DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(medicoSimulado.getId(), "Fernando Silva", "892083001", new DadosEndereco("rua silvia", "santa paula", "12388412", "Sao Paulo", "SP", "bloco B", "201"));
	    ResponseEntity<DadosDetalhamentoMedico> responseAtualizar = controller.atualizar(dadosAtualizacaoMedico);
		
	    verify(repository).getReferenceById(any(Long.class));
	    
	    verify(medicoSimulado).atualizarInformacoes(dadosAtualizacaoMedico);
	     
	    assertEquals(HttpStatus.OK, responseAtualizar.getStatusCode());
	     
	}

}

