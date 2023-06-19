package medico.apiconsultas.paciente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;

class PacienteTest {

	@Test
	@DisplayName("Deve alterar os dados do paciente, nome, telefone e endereco completo")
	void testAtualizarInformacoesPaciente_cenario1() {
		
		var endereco = new Endereco("rua santos", "santa paula", "12300777", "21", "bloco F", "Sao Paulo", "SP");
		
		Paciente paciente = new Paciente(null, "Ana", "ana@vold.med", "555555555", "477.528.639-74", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, "Ana Francisca", "444444444", new DadosEndereco("rua indaia", "mooca", "12299777", "Rio de Janeiro", "RJ", "bloco L", "16"));
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Ana Francisca", paciente.getNome());
		assertEquals("444444444", paciente.getTelefone());
		assertEquals("rua indaia", paciente.getEndereco().getLogradouro());
		assertEquals("mooca", paciente.getEndereco().getBairro());
		assertEquals("12299777", paciente.getEndereco().getCep());
		assertEquals("Rio de Janeiro", paciente.getEndereco().getCidade());
		assertEquals("RJ", paciente.getEndereco().getUf());
		assertEquals("bloco L", paciente.getEndereco().getComplemento());
		assertEquals("16", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do paciente, nome, telefone e endereco completo")
	void testAtualizarInformacoesPaciente_cenario2() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "BA");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, null, null));
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("Bahia", paciente.getEndereco().getCidade());
		assertEquals("BA", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o nome do paciente")
	void testAtualizarInformacoesPaciente_cenario3() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "BA");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, "Silvia Luz", null, new DadosEndereco(null, null, null, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia Luz", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("Bahia", paciente.getEndereco().getCidade());
		assertEquals("BA", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o telefone do paciente")
	void testAtualizarInformacoesPaciente_cenario4() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "BA");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, "987412056", new DadosEndereco(null, null, null, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("987412056", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("Bahia", paciente.getEndereco().getCidade());
		assertEquals("BA", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o endereco completo do paciente")
	void testAtualizarInformacoesPaciente_cenario5() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "BA");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco("rua teotonio", "vila ema", "02578412", "Minas Gerais", "MG", "casa 3", "2000"));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua teotonio", paciente.getEndereco().getLogradouro());
		assertEquals("vila ema", paciente.getEndereco().getBairro());
		assertEquals("02578412", paciente.getEndereco().getCep());
		assertEquals("Minas Gerais", paciente.getEndereco().getCidade());
		assertEquals("MG", paciente.getEndereco().getUf());
		assertEquals("casa 3", paciente.getEndereco().getComplemento());
		assertEquals("2000", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o logradouro do endereco do paciente")
	void testAtualizarInformacoesPaciente_cenario6() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "BA");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco("rua teotonio", null, null, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua teotonio", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("Bahia", paciente.getEndereco().getCidade());
		assertEquals("BA", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o bairro do endereco do paciente")
	void testAtualizarInformacoesPaciente_cenario7() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "BA");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, "bom retiro", null, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("bom retiro", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("Bahia", paciente.getEndereco().getCidade());
		assertEquals("BA", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o cep do endereco do paciente")
	void testAtualizarInformacoesPaciente_cenario8() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "BA");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, "03132125", null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("03132125", paciente.getEndereco().getCep());
		assertEquals("Bahia", paciente.getEndereco().getCidade());
		assertEquals("BA", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar a cidade do endereco do paciente")
	void testAtualizarInformacoesPaciente_cenario9() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "SP");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, "São Caetano do Sul", null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("São Caetano do Sul", paciente.getEndereco().getCidade());
		assertEquals("SP", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar uf do endereco do paciente")
	void testAtualizarInformacoesPaciente_cenario10() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "São Paulo", "SP");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, "MG", null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("São Paulo", paciente.getEndereco().getCidade());
		assertEquals("MG", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar complemento do endereco do paciente")
	void testAtualizarInformacoesPaciente_cenario11() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "São Paulo", "SP");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, "casa 3", null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("São Paulo", paciente.getEndereco().getCidade());
		assertEquals("SP", paciente.getEndereco().getUf());
		assertEquals("casa 3", paciente.getEndereco().getComplemento());
		assertEquals("2687", paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar numero do endereco do paciente")
	void testAtualizarInformacoesPaciente_cenario12() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "São Paulo", "SP");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, null, "400"));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Silvia", paciente.getNome());
		assertEquals("978524830", paciente.getTelefone());
		assertEquals("rua augusta", paciente.getEndereco().getLogradouro());
		assertEquals("tatuape", paciente.getEndereco().getBairro());
		assertEquals("09658410", paciente.getEndereco().getCep());
		assertEquals("São Paulo", paciente.getEndereco().getCidade());
		assertEquals("SP", paciente.getEndereco().getUf());
		assertEquals("bloco K", paciente.getEndereco().getComplemento());
		assertEquals("400", paciente.getEndereco().getNumero());
		
	}

}
