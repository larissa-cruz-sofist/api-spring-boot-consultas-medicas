package medico.apiconsultas.medico;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;
import medico.apiconsultas.paciente.DadosAtualizacaoPaciente;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import org.junit.jupiter.api.Test;

class MedicoTest {

	FakeValuesService fakeValuesService = new FakeValuesService(new Locale("pt-BR"), new RandomService());
	Faker faker = new Faker(new Locale("pt-BR"));

	private Medico montarMedicoRandomico() {
		Endereco endereco = new Endereco(faker.address().streetName(), faker.address().lastName(),
				fakeValuesService.numerify("########"), faker.address().buildingNumber(),
				faker.address().secondaryAddress(), faker.address().city(), faker.address().stateAbbr());
		var especialidade = Especialidade.CARDIOLOGIA;
		return new Medico(null, faker.name().fullName(), fakeValuesService.bothify("???????##@vold.med"),
				faker.phoneNumber().cellPhone(), fakeValuesService.numerify("######"), especialidade, endereco, true);
	}

	private Medico montarMedico() {
		Endereco endereco = new Endereco("Rua Exemplo", "Bairro Exemplo", "12345678", "123", "Complemento Exemplo",
				"Cidade Exemplo", "UF");
		var especialidade = Especialidade.DERMATOLOGIA;
		return new Medico(null, "Nome Inicial", "email@example.com", "123456789", "850147", especialidade, endereco,
				true);
	}

	@Test
	@DisplayName("Deve alterar os dados do médico, nome, telefone e endereco completo")
	void deveriaAtualizarInformacoesMedico() {

		Medico medico = montarMedico();

		// Dados para atualização
		Medico atualizacaoMedico = montarMedicoRandomico();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, atualizacaoMedico.getNome(),
				atualizacaoMedico.getTelefone(),
				new DadosEndereco(atualizacaoMedico.getEndereco().getLogradouro(),
						atualizacaoMedico.getEndereco().getBairro(), atualizacaoMedico.getEndereco().getCep(),
						atualizacaoMedico.getEndereco().getCidade(), atualizacaoMedico.getEndereco().getUf(),
						atualizacaoMedico.getEndereco().getComplemento(), atualizacaoMedico.getEndereco().getNumero()));

		// Atualiza as informações do Medico
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals(medico.getNome(), atualizacaoMedico.getNome());
		assertEquals(medico.getTelefone(), atualizacaoMedico.getTelefone());
		assertEquals(medico.getEndereco().getLogradouro(), atualizacaoMedico.getEndereco().getLogradouro());
		assertEquals(medico.getEndereco().getBairro(), atualizacaoMedico.getEndereco().getBairro());
		assertEquals(medico.getEndereco().getCep(), atualizacaoMedico.getEndereco().getCep());
		assertEquals(medico.getEndereco().getCidade(), atualizacaoMedico.getEndereco().getCidade());
		assertEquals(medico.getEndereco().getUf(), atualizacaoMedico.getEndereco().getUf());
		assertEquals(medico.getEndereco().getComplemento(), atualizacaoMedico.getEndereco().getComplemento());
		assertEquals(medico.getEndereco().getNumero(), atualizacaoMedico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Não deve alterar os dados do médico, nome, telefone e endereco completo")
	void deveriaNaoAtualizarInformacoesMedico() {

		Medico medico = montarMedico();

		// Dados para atualização
		// Medico atualizacaoMedico = montarMedicoRandomico();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(null, null, null, null, null, null, null));

		// Atualiza as informações do Medico
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar nome do medico")
	void deveriaAtualizarInformacoesMedicoNome() {

		Medico medico = montarMedico();

		// Dados para atualização
		var nomeNovo = faker.name().fullName();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, nomeNovo, null,
				new DadosEndereco(null, null, null, null, null, null, null));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals(nomeNovo, medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar telefone do medico")
	void deveriaAtualizarInformacoesMedicoTelefone() {

		Medico medico = montarMedico();

		// Dados para atualização
		var telefoneNovo = faker.phoneNumber().cellPhone();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, telefoneNovo,
				new DadosEndereco(null, null, null, null, null, null, null));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals(telefoneNovo, medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o endereco completo do medico")
	void deveriaAtualizarInformacoesMedicoEnderecoCompleto() {

		Medico medico = montarMedico();

		// Dados para atualização
		var logradouroNovo = faker.address().streetName();
		var bairroNovo = faker.address().lastName();
		var cepNovo = fakeValuesService.numerify("########");
		var cidadeNovo = faker.address().city();
		var ufNovo = faker.address().stateAbbr();
		var numeroNovo = faker.address().buildingNumber();
		var complementoNovo = faker.address().secondaryAddress();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(logradouroNovo, bairroNovo, cepNovo, cidadeNovo, ufNovo, complementoNovo,
						numeroNovo));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals(logradouroNovo, medico.getEndereco().getLogradouro());
		assertEquals(bairroNovo, medico.getEndereco().getBairro());
		assertEquals(cepNovo, medico.getEndereco().getCep());
		assertEquals(cidadeNovo, medico.getEndereco().getCidade());
		assertEquals(ufNovo, medico.getEndereco().getUf());
		assertEquals(complementoNovo, medico.getEndereco().getComplemento());
		assertEquals(numeroNovo, medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o logradouro do endereco do medico")
	void deveriaAtualizarInformacoesMedicoLogradouro() {

		Medico medico = montarMedico();

		// Dados para atualização
		var logradouroNovo = faker.address().streetName();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(logradouroNovo, null, null, null, null, null, null));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals(logradouroNovo, medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o bairro do endereco do medico")
	void deveriaAtualizarInformacoesMedicoBairro() {

		Medico medico = montarMedico();

		// Dados para atualização
		var bairroNovo = faker.address().lastName();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(null, bairroNovo, null, null, null, null, null));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals(bairroNovo, medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar o cep do endereco do medico")
	void deveriaAtualizarInformacoesMedicoCep() {

		Medico medico = montarMedico();

		// Dados para atualização
		var cepNovo = fakeValuesService.numerify("########");

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(null, null, cepNovo, null, null, null, null));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals(cepNovo, medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar a cidade do endereco do medico")
	void deveriaAtualizarInformacoesMedicoCidade() {

		Medico medico = montarMedico();

		// Dados para atualização
		var cidadeNovo = faker.address().city();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(null, null, null, cidadeNovo, null, null, null));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals(cidadeNovo, medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar uf do endereco do medico")
	void deveriaAtualizarInformacoesMedicoUf() {

		Medico medico = montarMedico();

		// Dados para atualização
		var ufNovo = faker.address().stateAbbr();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(null, null, null, null, ufNovo, null, null));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals(ufNovo, medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar complemento do endereco do medico")
	void deveriaAtualizarInformacoesMedicoComplemento() {

		Medico medico = montarMedico();

		// Dados para atualização
		var complementoNovo = faker.address().secondaryAddress();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(null, null, null, null, null, complementoNovo, null));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals(complementoNovo, medico.getEndereco().getComplemento());
		assertEquals("123", medico.getEndereco().getNumero());

	}

	@Test
	@DisplayName("Deve alterar numero do endereco do medico")
	void deveriaAtualizarInformacoesMedicoNumero() {

		Medico medico = montarMedico();

		// Dados para atualização
		var numeroNovo = faker.address().buildingNumber();

		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null,
				new DadosEndereco(null, null, null, null, null, null, numeroNovo));

		// Atualiza as informações do paciente
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		assertEquals("Nome Inicial", medico.getNome());
		assertEquals("123456789", medico.getTelefone());
		assertEquals("Rua Exemplo", medico.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", medico.getEndereco().getBairro());
		assertEquals("12345678", medico.getEndereco().getCep());
		assertEquals("Cidade Exemplo", medico.getEndereco().getCidade());
		assertEquals("UF", medico.getEndereco().getUf());
		assertEquals("Complemento Exemplo", medico.getEndereco().getComplemento());
		assertEquals(numeroNovo, medico.getEndereco().getNumero());

	}

}
