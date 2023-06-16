package medico.apiconsultas.medico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;

import org.junit.jupiter.api.Test;

class MedicoTest {

	@Test
	@DisplayName("Deve alterar os dados do médico, nome, telefone e endereco completo")
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
	@DisplayName("Não deve alterar os dados do médico, nome, telefone e endereco completo")
	void testAtualizarInformacoesMedico_cenario2() {
		
		var especialidade = Especialidade.DERMATOLOGIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, telefone e endereco completo")
	void testAtualizarInformacoesMedico_cenario3() {
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, "Marcos Luis", null, new DadosEndereco(null, null, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Marcos Luis", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, nome e endereco completo")
	void testAtualizarInformacoesMedico_cenario4() {
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, "984102580", new DadosEndereco(null, null, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("984102580", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, nome e telefone")
	void testAtualizarInformacoesMedico_cenario5() {
		
		var especialidade = Especialidade.GINECOLOGIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco("rua aroareiras", "perdizes", "03233125", "São Paulo", "SP", "casa 1", "500"));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua aroareiras", medico.getEndereco().getLogradouro());
		assertEquals("perdizes", medico.getEndereco().getBairro());
		assertEquals("03233125", medico.getEndereco().getCep());
		assertEquals("São Paulo", medico.getEndereco().getCidade());
		assertEquals("SP", medico.getEndereco().getUf());
		assertEquals("casa 1", medico.getEndereco().getComplemento());
		assertEquals("500", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, exceto endereco logradouro")
	void testAtualizarInformacoesMedico_cenario6() {
		
		var especialidade = Especialidade.GINECOLOGIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco("rua aroareiras", null, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua aroareiras", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, exceto endereco bairro")
	void testAtualizarInformacoesMedico_cenario7() {
		
		var especialidade = Especialidade.DERMATOLOGIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, "santa maria", null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("santa maria", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, exceto endereco cep")
	void testAtualizarInformacoesMedico_cenario8() {
		
		var especialidade = Especialidade.DERMATOLOGIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, "03132125", null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("03132125", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, exceto endereco cidade")
	void testAtualizarInformacoesMedico_cenario9() {
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, "Rio de Janeiro", null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Rio de Janeiro", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, exceto endereco uf")
	void testAtualizarInformacoesMedico_cenario10() {
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, null, "RJ", null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("RJ", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, exceto endereco complemento")
	void testAtualizarInformacoesMedico_cenario11() {
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, null, null, "casa 3", null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("casa 3", medico.getEndereco().getComplemento());
		assertEquals("26", medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, exceto endereco numero")
	void testAtualizarInformacoesMedico_cenario12() {
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco("rua paraiba", "bela vista", "02578456", "26", "bloco F", "Bahia", "BA");
		
		Medico medico = new Medico(null, "Cesar", "cesar@vold.med", "892083001", "805558", especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, null, null, null, "150"));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals("Cesar", medico.getNome());
		assertEquals("892083001", medico.getTelefone());
		assertEquals("rua paraiba", medico.getEndereco().getLogradouro());
		assertEquals("bela vista", medico.getEndereco().getBairro());
		assertEquals("02578456", medico.getEndereco().getCep());
		assertEquals("Bahia", medico.getEndereco().getCidade());
		assertEquals("BA", medico.getEndereco().getUf());
		assertEquals("bloco F", medico.getEndereco().getComplemento());
		assertEquals("150", medico.getEndereco().getNumero());
		
		
	}

}
