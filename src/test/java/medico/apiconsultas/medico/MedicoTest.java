package medico.apiconsultas.medico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;

import org.junit.jupiter.api.Test;

class MedicoTest {

	@Test
	@DisplayName("Deve alterar os dados do médico, nome, telefone e endereco")
	void testAtualizarInformacoesMedico_cenario1() {
		
		var especialidade = Especialidade.CARDIOLOGIA;
		var endereco = new Endereco("rua baraldi", "santa paula", "12300412", "20", "bloco D", "Sao Paulo", "SP");
		
		Medico medico = new Medico(null, "Marcos", "marcoslinhares@vold.med", "801208", "892083001", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, "Marcos Alberto", "892083221", new DadosEndereco("rua paes de barros", "vila mariana", "03125789", "Minas Gerais", "MG", "bloco H", "33"));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Marcos Alberto", medico.getNome());
		assertEquals("892083221", medico.getTelefone());
		assertEquals("rua paes de barros", medico.getEndereco().getLogradouro());
		assertEquals("vila mariana", medico.getEndereco().getBairro());
		assertEquals("03125789", medico.getEndereco().getCep());
		assertEquals("Minas Gerais", medico.getEndereco().getCidade());
		assertEquals("MG", medico.getEndereco().getUf());
		assertEquals("bloco H", medico.getEndereco().getComplemento());
		assertEquals("33", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, nome, telefone e endereco")
	void testAtualizarInformacoesMedico_cenario2() {
		
		var especialidade = Especialidade.DERMATOLOGIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "805558", "204083001", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, "Cesar", "204083001", new DadosEndereco("rua paraiba", "bela vista", "02578456", "Bahia", "BA", "bloco F", "26"));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("204083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}

}
