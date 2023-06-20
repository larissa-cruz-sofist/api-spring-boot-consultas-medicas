package medico.apiconsultas.medico;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;

import medico.apiconsultas.endereco.DadosEndereco;
import medico.apiconsultas.endereco.Endereco;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import org.junit.jupiter.api.Test;

class MedicoTest {
	
    FakeValuesService fakeValuesService = new FakeValuesService(new Locale("pt-BR"), new RandomService());
    Faker faker = new Faker(new Locale("pt-BR"));
    

	@Test
	@DisplayName("Deve alterar os dados do médico, nome, telefone e endereco completo")
	void testAtualizarInformacoesMedico_cenario1() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var logradouro2 = faker.address().streetName();
		var bairro2 = faker.address().lastName();
		var cep2 = fakeValuesService.numerify("########");
		var cidade2 = faker.address().city();
		var uf2 = faker.address().stateAbbr();
		var numero2 = faker.address().buildingNumber();
		var complemento2 = faker.address().secondaryAddress();
		
		var nome2 = faker.name().fullName();
		var telefone2 = faker.phoneNumber().cellPhone();
		
		var especialidade = Especialidade.CARDIOLOGIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, crm1, telefone1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, nome2, telefone2, new DadosEndereco(logradouro2, bairro2, cep2, cidade2, uf2, complemento2, numero2));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome2, medico.getNome());
		assertEquals(telefone2, medico.getTelefone());
		assertEquals(logradouro2, medico.getEndereco().getLogradouro());
		assertEquals(bairro2, medico.getEndereco().getBairro());
		assertEquals(cep2, medico.getEndereco().getCep());
		assertEquals(cidade2, medico.getEndereco().getCidade());
		assertEquals(uf2, medico.getEndereco().getUf());
		assertEquals(complemento2, medico.getEndereco().getComplemento());
		assertEquals(numero2, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do médico, nome, telefone e endereco completo")
	void testAtualizarInformacoesMedico_cenario2() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var especialidade = Especialidade.DERMATOLOGIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar nome do medico")
	void testAtualizarInformacoesMedico_cenario3() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var nome2 = faker.name().fullName();
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, nome2, null, new DadosEndereco(null, null, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome2, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar telefone do medico")
	void testAtualizarInformacoesMedico_cenario4() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var telefone2 = faker.phoneNumber().cellPhone();
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, telefone2, new DadosEndereco(null, null, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone2, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar o endereco completo do medico")
	void testAtualizarInformacoesMedico_cenario5() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var logradouro2 = faker.address().streetName();
		var bairro2 = faker.address().lastName();
		var cep2 = fakeValuesService.numerify("########");
		var cidade2 = faker.address().city();
		var uf2 = faker.address().stateAbbr();
		var numero2 = faker.address().buildingNumber();
		var complemento2 = faker.address().secondaryAddress();
		
		var especialidade = Especialidade.GINECOLOGIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(logradouro2, bairro2, cep2, cidade2, uf2, complemento2, numero2));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro2, medico.getEndereco().getLogradouro());
		assertEquals(bairro2, medico.getEndereco().getBairro());
		assertEquals(cep2, medico.getEndereco().getCep());
		assertEquals(cidade2, medico.getEndereco().getCidade());
		assertEquals(uf2, medico.getEndereco().getUf());
		assertEquals(complemento2, medico.getEndereco().getComplemento());
		assertEquals(numero2, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar o logradouro do endereco do medico")
	void testAtualizarInformacoesMedico_cenario6() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var logradouro2 = faker.address().streetName();
		
		var especialidade = Especialidade.GINECOLOGIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(logradouro2, null, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro2, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar o bairro do endereco do medico")
	void testAtualizarInformacoesMedico_cenario7() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var bairro2 = faker.address().lastName();
		
		var especialidade = Especialidade.DERMATOLOGIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, bairro2, null, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro2, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar o cep do endereco do medico")
	void testAtualizarInformacoesMedico_cenario8() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var cep2 = fakeValuesService.numerify("########");
		
		var especialidade = Especialidade.DERMATOLOGIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, cep2, null, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep2, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar a cidade do endereco do medico")
	void testAtualizarInformacoesMedico_cenario9() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var cidade2 = faker.address().city();
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, cidade2, null, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade2, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar uf do endereco do medico")
	void testAtualizarInformacoesMedico_cenario10() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var uf2 = faker.address().stateAbbr();
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, null, uf2, null, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf2, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar complemento do endereco do medico")
	void testAtualizarInformacoesMedico_cenario11() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var complemento2 = faker.address().secondaryAddress();
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, null, null, complemento2, null));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento2, medico.getEndereco().getComplemento());
		assertEquals(numero1, medico.getEndereco().getNumero());
		
		
	}
	
	@Test
	@DisplayName("Deve alterar numero do endereco do medico")
	void testAtualizarInformacoesMedico_cenario12() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var crm1 = fakeValuesService.numerify("######");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var numero2 = faker.address().buildingNumber();
		
		var especialidade = Especialidade.ORTOPEDIA;
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Medico medico = new Medico(null, nome1, email1, telefone1, crm1, especialidade, endereco, true);
		
		DadosAtualizacaoMedico dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, new DadosEndereco(null, null, null, null, null, null, numero2));
		
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		
		assertEquals(nome1, medico.getNome());
		assertEquals(telefone1, medico.getTelefone());
		assertEquals(logradouro1, medico.getEndereco().getLogradouro());
		assertEquals(bairro1, medico.getEndereco().getBairro());
		assertEquals(cep1, medico.getEndereco().getCep());
		assertEquals(cidade1, medico.getEndereco().getCidade());
		assertEquals(uf1, medico.getEndereco().getUf());
		assertEquals(complemento1, medico.getEndereco().getComplemento());
		assertEquals(numero2, medico.getEndereco().getNumero());
		
		
	}

}
