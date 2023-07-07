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

	private Paciente montarPacienteInicial() {
    Endereco endereco = new Endereco("Rua Exemplo", "Bairro Exemplo", "12345678", "123", "Complemento Exemplo", "Cidade Exemplo", "UF");
    return new Paciente(null, "Nome Inicial", "email@example.com", "123456789", "CPF Exemplo", endereco, true);
}

	@Test
	@DisplayName("Deve alterar os dados do paciente, nome, telefone e endereco completo")
	void testAtualizarInformacoesPacienteNomeTelefoneEnderecoCompleto() {

		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var logradouroNovo = faker.address().streetName();
		var bairroNovo = faker.address().lastName();
		var cepNovo = fakeValuesService.numerify("########");
		var cidadeNovo = faker.address().city();
		var ufNovo = faker.address().stateAbbr();
		var numeroNovo = faker.address().buildingNumber();
		var complementoNovo = faker.address().secondaryAddress();
		
		var nomeNovo = faker.name().fullName();
		var telefoneNovo = faker.phoneNumber().cellPhone();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, nomeNovo, telefoneNovo, new DadosEndereco(logradouroNovo, bairroNovo, cepNovo, cidadeNovo, ufNovo, complementoNovo, numeroNovo));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nomeNovo, pacienteInicial.getNome());
		assertEquals(telefoneNovo, pacienteInicial.getTelefone());
		assertEquals(logradouroNovo, pacienteInicial.getEndereco().getLogradouro());
		assertEquals(bairroNovo, pacienteInicial.getEndereco().getBairro());
		assertEquals(cepNovo, pacienteInicial.getEndereco().getCep());
		assertEquals(cidadeNovo, pacienteInicial.getEndereco().getCidade());
		assertEquals(ufNovo, pacienteInicial.getEndereco().getUf());
		assertEquals(complementoNovo, pacienteInicial.getEndereco().getComplemento());
		assertEquals(numeroNovo, pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do paciente, nome, telefone e endereco completo")
	void testNaoAtualizarInformacoesPaciente() {

		Paciente pacienteInicial = montarPacienteInicial();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, null, null));
		
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o nome do paciente")
	void testAtualizarInformacoesPacienteNome() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var nomeNovo = faker.name().fullName();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, nomeNovo, null, new DadosEndereco(null, null, null, null, null, null, null));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nomeNovo, pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());

		
	}
	
	@Test
	@DisplayName("Deve alterar o telefone do paciente")
	void testAtualizarInformacoesPacienteTelefone() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var telefoneNovo = faker.phoneNumber().cellPhone();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, telefoneNovo, new DadosEndereco(null, null, null, null, null, null, null));

		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals(telefoneNovo, pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o endereco completo do paciente")
	void testAtualizarInformacoesPacienteEnderecoCompleto() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var logradouroNovo = faker.address().streetName();
		var bairroNovo = faker.address().lastName();
		var cepNovo = fakeValuesService.numerify("########");
		var cidadeNovo = faker.address().city();
		var ufNovo = faker.address().stateAbbr();
		var numeroNovo = faker.address().buildingNumber();
		var complementoNovo = faker.address().secondaryAddress();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(logradouroNovo, bairroNovo, cepNovo, cidadeNovo, ufNovo, complementoNovo, numeroNovo));

		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals(logradouroNovo, pacienteInicial.getEndereco().getLogradouro());
		assertEquals(bairroNovo, pacienteInicial.getEndereco().getBairro());
		assertEquals(cepNovo, pacienteInicial.getEndereco().getCep());
		assertEquals(cidadeNovo, pacienteInicial.getEndereco().getCidade());
		assertEquals(ufNovo, pacienteInicial.getEndereco().getUf());
		assertEquals(complementoNovo, pacienteInicial.getEndereco().getComplemento());
		assertEquals(numeroNovo, pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o logradouro do endereco do paciente")
	void testAtualizarInformacoesPacienteLogradouro() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var logradouroNovo = faker.address().streetName();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(logradouroNovo, null, null, null, null, null, null));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals(logradouroNovo, pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o bairro do endereco do paciente")
	void testAtualizarInformacoesPacienteBairro() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var bairroNovo = faker.address().lastName();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, bairroNovo, null, null, null, null, null));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals(bairroNovo, pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o cep do endereco do paciente")
	void testAtualizarInformacoesPacienteCep() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var cepNovo = fakeValuesService.numerify("########");
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, cepNovo, null, null, null, null));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals(cepNovo, pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar a cidade do endereco do paciente")
	void testAtualizarInformacoesPacienteCidade() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var cidadeNovo = faker.address().city();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, cidadeNovo, null, null, null));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals(cidadeNovo, pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar uf do endereco do paciente")
	void testAtualizarInformacoesPacienteUf() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var ufNovo = faker.address().stateAbbr();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, ufNovo, null, null));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals(ufNovo, pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar complemento do endereco do paciente")
	void testAtualizarInformacoesPacienteComplemento() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var complementoNovo = faker.address().secondaryAddress();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, complementoNovo, null));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals(complementoNovo, pacienteInicial.getEndereco().getComplemento());
		assertEquals("123", pacienteInicial.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar numero do endereco do paciente")
	void testAtualizarInformacoesPacienteNumero() {
		
		Paciente pacienteInicial = montarPacienteInicial();
		
		//Dados para atualização
		var numeroNovo = faker.address().buildingNumber();
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, null, numeroNovo));
		
		// Atualiza as informações do paciente
		pacienteInicial.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Nome Inicial", pacienteInicial.getNome());
		assertEquals("123456789", pacienteInicial.getTelefone());
		assertEquals("Rua Exemplo", pacienteInicial.getEndereco().getLogradouro());
		assertEquals("Bairro Exemplo", pacienteInicial.getEndereco().getBairro());
		assertEquals("12345678", pacienteInicial.getEndereco().getCep());
		assertEquals("Cidade Exemplo", pacienteInicial.getEndereco().getCidade());
		assertEquals("UF", pacienteInicial.getEndereco().getUf());
		assertEquals("Complemento Exemplo", pacienteInicial.getEndereco().getComplemento());
		assertEquals(numeroNovo, pacienteInicial.getEndereco().getNumero());
		
	}

}
