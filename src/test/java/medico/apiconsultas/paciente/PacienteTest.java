package medico.apiconsultas.paciente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;

class PacienteTest {

	@Test
	void testAtualizarInformacoesPaciente() {
		
		var endereco = new Endereco("rua santos", "santa paula", "12300777", "21", "bloco F", "Sao Paulo", "SP");
		
		Paciente paciente = new Paciente(null, "Ana", "ana@vold.med", "555555555", "477.528.639-74", endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, "Ana Francisca", "444444444", new DadosEndereco("rua santos", "santa paula", "12300777", "Sao Paulo", "SP", "bloco F", "21"));
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals("Ana Francisca", paciente.getNome());
		assertEquals("444444444", paciente.getTelefone());
		
		
	}

}
