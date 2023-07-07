package medico.apiconsultas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import medico.apiconsultas.controllers.PacienteController;
import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;
import medico.apiconsultas.paciente.*;

import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PacienteControllerTest {

	@Autowired
	private MockMvc mvc;

	@Mock
	private PacienteRepository repository;

	@InjectMocks
	private PacienteController controller;

	@Test
	@DisplayName("Deveria devolver codigo http 400 quando informacoes do paciente estao invalidas")
	@WithMockUser
	void deveriaRetornarStatus400QuandoCadastrarPacienteSemInformacoesBody() throws Exception {
		var response = mvc.perform(post("/pacientes"))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	@DisplayName("Deveria devolver codigo http 200 quando informacoes do paciente estao validas - cadastrar paciente")
	@WithMockUser
	void deveriaRetornarStatus200QuandoCadastrarPacienteInformacoesValidas() throws Exception {

		DadosCadastroPaciente dadosCadastroPaciente = new DadosCadastroPaciente("Laura", "laura@vold.med", "999999999",
				"589.874-63",
				new DadosEndereco("rua visconde", "santa paula", "12366612", "Sao Paulo", "SP", "bloco E", "210"));

		Paciente pacienteSalvo = new Paciente(dadosCadastroPaciente);
		when(repository.save(any(Paciente.class))).thenReturn(pacienteSalvo);

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/");
		ResponseEntity<DadosDetalhamentoPaciente> response = controller.cadastrar(dadosCadastroPaciente, uriBuilder);

		verify(repository).save(any(Paciente.class));

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		assertThat(response.getBody())
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(new DadosDetalhamentoPaciente(pacienteSalvo));

	}

	@ParameterizedTest
	@DisplayName("Deveria devolver codigo http 204 quando informacoes estao validas - excluir paciente")
	@MethodSource("argumentoAtivo")
	@WithMockUser
	void deveriaRetornarStatus204QuandoExcluirPacienteExistente(boolean ativo) throws Exception {

		var endereco = new Endereco("rua andreas", "sao jose", "19999977", "2288", "bloco A", "Bahia", "BA");

		Paciente paciente = new Paciente(Long.valueOf(123), "Carlos", "carlos@voll.med", "965412298", "422.784.666-01",
				endereco, ativo);
		when(repository.getReferenceById(any(Long.class))).thenReturn(paciente);

		ResponseEntity<Void> response = controller.excluir(paciente.getId());

		assertEquals(ResponseEntity.noContent().build(), response);

	}

	private static Stream<Arguments> argumentoAtivo() {
		return Stream.of(
				Arguments.of(true),
				Arguments.of(false));

	}

	@Test
	@DisplayName("Deveria devolver codigo http 404 quando paciente nao existe - excluir paciente")
	@WithMockUser
	void deveriaRetornarStatus404QuandoExcluirPacienteNaoExistente() throws Exception {
		var response = mvc.perform(delete("/pacientes/1000000000"))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

	}

	@Test
	@DisplayName("Deveria devolver codigo http 200 quando informacoes do paciente estao validas - alterar paciente")
	@WithMockUser
	void deveriaRetornarStatus200QuandoAlterarPacienteExistente() throws Exception {

		Paciente pacienteSimulado = Mockito.mock(Paciente.class);
		when(repository.getReferenceById(any(Long.class))).thenReturn(pacienteSimulado);

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(pacienteSimulado.getId(),
				"Lorena Silva", "777777777",
				new DadosEndereco("rua janja", "santa paula", "92388412", "Sao Paulo", "SP", "bloco C", "470"));
		ResponseEntity<DadosDetalhamentoPaciente> responseAtualizar = controller.atualizar(dadosAtualizacaoPaciente);

		verify(repository).getReferenceById(any(Long.class));

		verify(pacienteSimulado).atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals(HttpStatus.OK, responseAtualizar.getStatusCode());

	}

}
