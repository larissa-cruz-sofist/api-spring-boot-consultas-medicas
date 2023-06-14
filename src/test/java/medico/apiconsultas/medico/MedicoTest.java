package medico.apiconsultas.medico;

import static org.junit.jupiter.api.Assertions.*;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;

import org.junit.jupiter.api.Test;

class MedicoTest {

	@Test
	void testAtualizarInformacoesMedico() {
		
		var especialidade = Especialidade.CARDIOLOGIA;
		var endereco = new Endereco("rua baraldi", "santa paula", "12300412", "20", "bloco D", "Sao Paulo", "SP");
		
		Medico medico = new Medico(null, "Marcos", "marcoslinhares@vold.med", "801208", "892083001", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, "Marcos Alberto", "892083221", new DadosEndereco("rua baraldi", "santa paula", "12300412", "Sao Paulo", "SP", "bloco D", "20"));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Marcos Alberto", medico.getNome());
		assertEquals("892083221", medico.getTelefone());
		
		
	}

}
