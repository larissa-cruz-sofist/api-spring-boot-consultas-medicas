package medico.apiconsultas.paciente;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

class PacienteTest {

	FakeValuesService fakeValuesService = new FakeValuesService(new Locale("pt-BR"), new RandomService());
	Faker faker = new Faker(new Locale("pt-BR"));

	private Paciente montarPacienteRandomico() {
		Endereco endereco = new Endereco(faker.address().streetName(), faker.address().lastName(),
				fakeValuesService.numerify("########"), faker.address().buildingNumber(),
				faker.address().secondaryAddress(), faker.address().city(), faker.address().stateAbbr());
		return new Paciente(null, faker.name().fullName(), fakeValuesService.bothify("???????##@vold.med"),
				faker.phoneNumber().cellPhone(), fakeValuesService.numerify("###.###.###-##"), endereco, true);
	}

	private Paciente montarPaciente() {
		Endereco endereco = new Endereco("Rua Exemplo", "Bairro Exemplo", "12345678", "123", "Complemento Exemplo",
				"Cidade Exemplo", "UF");
		return new Paciente(null, "Nome Inicial", "email@example.com", "123456789", "CPF Exemplo", endereco, true);
	}

	@Test
	@DisplayName("Deve alterar os dados do paciente, nome, telefone e endereco completo")
	void deveriaAtualizarInformacoesPacienteNomeTelefoneEnderecoCompleto() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		Paciente atualizacaoPaciente = montarPacienteRandomico();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null,
				atualizacaoPaciente.getNome(), atualizacaoPaciente.getTelefone(),
				new DadosEndereco(atualizacaoPaciente.getEndereco().getLogradouro(),
						atualizacaoPaciente.getEndereco().getBairro(), atualizacaoPaciente.getEndereco().getCep(),
						atualizacaoPaciente.getEndereco().getCidade(), atualizacaoPaciente.getEndereco().getUf(),
						atualizacaoPaciente.getEndereco().getComplemento(),
						atualizacaoPaciente.getEndereco().getNumero()));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals(paciente.getNome(), atualizacaoPaciente.getNome());
		assertEquals(paciente.getTelefone(), atualizacaoPaciente.getTelefone());
		assertEquals(paciente.getEndereco().getLogradouro(), atualizacaoPaciente.getEndereco().getLogradouro());
		assertEquals(paciente.getEndereco().getBairro(), atualizacaoPaciente.getEndereco().getBairro());
		assertEquals(paciente.getEndereco().getCep(), atualizacaoPaciente.getEndereco().getCep());
		assertEquals(paciente.getEndereco().getCidade(), atualizacaoPaciente.getEndereco().getCidade());
		assertEquals(paciente.getEndereco().getUf(), atualizacaoPaciente.getEndereco().getUf());
		assertEquals(paciente.getEndereco().getComplemento(), atualizacaoPaciente.getEndereco().getComplemento());
		assertEquals(paciente.getEndereco().getNumero(), atualizacaoPaciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Não deve alterar os dados do paciente, nome, telefone e endereco completo")
	void deveriaNaoAtualizarInformacoesPaciente() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		// Paciente atualizacaoPaciente = montarPacienteRandomico();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(null, null, null, null, null, null, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o nome do paciente")
	void deveriaAtualizarInformacoesPacienteNome() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var nomeNovo = faker.name().fullName();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, nomeNovo, null,
				new DadosEndereco(null, null, null, null, null, null, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals(nomeNovo, paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o telefone do paciente")
	void deveriaAtualizarInformacoesPacienteTelefone() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var telefoneNovo = faker.phoneNumber().cellPhone();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, telefoneNovo,
				new DadosEndereco(null, null, null, null, null, null, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals(telefoneNovo, paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o endereco completo do paciente")
	void deveriaAtualizarInformacoesPacienteEnderecoCompleto() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var logradouroNovo = faker.address().streetName();
		var bairroNovo = faker.address().lastName();
		var cepNovo = fakeValuesService.numerify("########");
		var cidadeNovo = faker.address().city();
		var ufNovo = faker.address().stateAbbr();
		var numeroNovo = faker.address().buildingNumber();
		var complementoNovo = faker.address().secondaryAddress();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(logradouroNovo, bairroNovo, cepNovo, cidadeNovo, ufNovo, complementoNovo,
						numeroNovo));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals(logradouroNovo, paciente.getEndereco().getLogradouro());
		assertEquals(bairroNovo, paciente.getEndereco().getBairro());
		assertEquals(cepNovo, paciente.getEndereco().getCep());
		assertEquals(cidadeNovo, paciente.getEndereco().getCidade());
		assertEquals(ufNovo, paciente.getEndereco().getUf());
		assertEquals(complementoNovo, paciente.getEndereco().getComplemento());
		assertEquals(numeroNovo, paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o logradouro do endereco do paciente")
	void deveriaAtualizarInformacoesPacienteLogradouro() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var logradouroNovo = faker.address().streetName();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(logradouroNovo, null, null, null, null, null, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals(logradouroNovo, paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o bairro do endereco do paciente")
	void deveriaAtualizarInformacoesPacienteBairro() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var bairroNovo = faker.address().lastName();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(null, bairroNovo, null, null, null, null, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals(bairroNovo, paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o cep do endereco do paciente")
	void deveriaAtualizarInformacoesPacienteCep() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var cepNovo = fakeValuesService.numerify("########");

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(null, null, cepNovo, null, null, null, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals(cepNovo, paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar a cidade do endereco do paciente")
	void deveriaAtualizarInformacoesPacienteCidade() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var cidadeNovo = faker.address().city();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(null, null, null, cidadeNovo, null, null, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals(cidadeNovo, paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar uf do endereco do paciente")
	void deveriaAtualizarInformacoesPacienteUf() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var ufNovo = faker.address().stateAbbr();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(null, null, null, null, ufNovo, null, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals(ufNovo, paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar complemento do endereco do paciente")
	void deveriaAtualizarInformacoesPacienteComplemento() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var complementoNovo = faker.address().secondaryAddress();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(null, null, null, null, null, complementoNovo, null));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals(complementoNovo, paciente.getEndereco().getComplemento());
		assertEquals("123", paciente.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar numero do endereco do paciente")
	void deveriaAtualizarInformacoesPacienteNumero() {

		Paciente paciente = montarPaciente();

		// Dados para atualização
		var numeroNovo = faker.address().buildingNumber();

		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null,
				new DadosEndereco(null, null, null, null, null, null, numeroNovo));

		// Atualiza as informações do paciente
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

		assertEquals("Nome Inicial", paciente.getNome());
		assertEquals("123456789", paciente.getTelefone());
		assertEquals("Rua Exemplo", paciente.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", paciente.getEndereco().getBairro());
		assertEquals("12345678", paciente.getEndereco().getCep());
		assertEquals("Cidade Exemplo", paciente.getEndereco().getCidade());
		assertEquals("UF", paciente.getEndereco().getUf());
		assertEquals("Complemento Exemplo", paciente.getEndereco().getComplemento());
		assertEquals(numeroNovo, paciente.getEndereco().getNumero());

	}

}
