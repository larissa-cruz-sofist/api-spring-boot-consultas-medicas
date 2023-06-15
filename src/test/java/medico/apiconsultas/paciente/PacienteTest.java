package medico.apiconsultas.paciente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;

class PacienteTest {

	@Test
	@DisplayName("Deve alterar os dados do paciente, nome, telefone e endereco")
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
	@DisplayName("Não deve alterar os dados do paciente, nome, telefone e endereco")
	void testAtualizarInformacoesPaciente_cenario2() {
		
		var endereco = new Endereco("rua augusta", "tatuape", "09658410", "2687", "bloco K", "Bahia", "BA");
		
		Paciente paciente = new Paciente(null, "Silvia", "silvia@vold.med", "978524830", "433.599.449-36", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, "Silvia", "978524830", new DadosEndereco("rua augusta", "tatuape", "09658410", "Bahia", "BA", "bloco K", "2687"));
		
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

}
