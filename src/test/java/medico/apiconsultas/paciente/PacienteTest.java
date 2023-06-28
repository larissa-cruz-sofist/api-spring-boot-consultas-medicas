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
    

	@Test
	@DisplayName("Deve alterar os dados do paciente, nome, telefone e endereco completo")
	void testAtualizarInformacoesPaciente() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
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
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, nome2, telefone2, new DadosEndereco(logradouro2, bairro2, cep2, cidade2, uf2, complemento2, numero2));
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome2, paciente.getNome());
		assertEquals(telefone2, paciente.getTelefone());
		assertEquals(logradouro2, paciente.getEndereco().getLogradouro());
		assertEquals(bairro2, paciente.getEndereco().getBairro());
		assertEquals(cep2, paciente.getEndereco().getCep());
		assertEquals(cidade2, paciente.getEndereco().getCidade());
		assertEquals(uf2, paciente.getEndereco().getUf());
		assertEquals(complemento2, paciente.getEndereco().getComplemento());
		assertEquals(numero2, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Não deve alterar os dados do paciente, nome, telefone e endereco completo")
	void testNaoAtualizarInformacoesPaciente() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, null, null));
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o nome do paciente")
	void testAtualizarInformacoesPacienteNome() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var nome2 = faker.name().fullName();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, nome2, null, new DadosEndereco(null, null, null, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome2, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o telefone do paciente")
	void testAtualizarInformacoesPacienteTelefone() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var telefone2 = faker.phoneNumber().cellPhone();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, telefone2, new DadosEndereco(null, null, null, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone2, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o endereco completo do paciente")
	void testAtualizarInformacoesPacienteEnderecoCompleto() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var logradouro2 = faker.address().streetName();
		var bairro2 = faker.address().lastName();
		var cep2 = fakeValuesService.numerify("########");
		var cidade2 = faker.address().city();
		var uf2 = faker.address().stateAbbr();
		var numero2 = faker.address().buildingNumber();
		var complemento2 = faker.address().secondaryAddress();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(logradouro2, bairro2, cep2, cidade2, uf2, complemento2, numero2));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro2, paciente.getEndereco().getLogradouro());
		assertEquals(bairro2, paciente.getEndereco().getBairro());
		assertEquals(cep2, paciente.getEndereco().getCep());
		assertEquals(cidade2, paciente.getEndereco().getCidade());
		assertEquals(uf2, paciente.getEndereco().getUf());
		assertEquals(complemento2, paciente.getEndereco().getComplemento());
		assertEquals(numero2, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o logradouro do endereco do paciente")
	void testAtualizarInformacoesPacienteLogradouro() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var logradouro2 = faker.address().streetName();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(logradouro2, null, null, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro2, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o bairro do endereco do paciente")
	void testAtualizarInformacoesPacienteBairro() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var bairro2 = faker.address().lastName();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, bairro2, null, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro2, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar o cep do endereco do paciente")
	void testAtualizarInformacoesPacienteCep() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var cep2 = fakeValuesService.numerify("########");
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, cep2, null, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep2, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar a cidade do endereco do paciente")
	void testAtualizarInformacoesPacienteCidade() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var cidade2 = faker.address().city();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, cidade2, null, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade2, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar uf do endereco do paciente")
	void testAtualizarInformacoesPacienteUf() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var uf2 = faker.address().stateAbbr();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, uf2, null, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf2, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar complemento do endereco do paciente")
	void testAtualizarInformacoesPacienteComplemento() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var complemento2 = faker.address().secondaryAddress();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, complemento2, null));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento2, paciente.getEndereco().getComplemento());
		assertEquals(numero1, paciente.getEndereco().getNumero());
		
	}
	
	@Test
	@DisplayName("Deve alterar numero do endereco do paciente")
	void testAtualizarInformacoesPacienteNumero() {
		
		var logradouro1 = faker.address().streetName();
		var bairro1 = faker.address().lastName();
		var cep1 = fakeValuesService.numerify("########");
		var cidade1 = faker.address().city();
		var uf1 = faker.address().stateAbbr();
		var numero1 = faker.address().buildingNumber();
		var complemento1 = faker.address().secondaryAddress();
		
		var nome1 = faker.name().fullName();
		var email1 = fakeValuesService.bothify("???????##@vold.med");
		var cpf1 = fakeValuesService.bothify("###.###.###-##");
		var telefone1 = faker.phoneNumber().cellPhone();
		
		var numero2 = faker.address().buildingNumber();
		
		var endereco = new Endereco(logradouro1, bairro1, cep1, numero1, complemento1, cidade1, uf1);
		
		Paciente paciente = new Paciente(null, nome1, email1, telefone1, cpf1, endereco, true);
		
		DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, new DadosEndereco(null, null, null, null, null, null, numero2));;
		
		paciente.atualizarInformacoes(dadosAtualizacaoPaciente);
		
		assertEquals(nome1, paciente.getNome());
		assertEquals(telefone1, paciente.getTelefone());
		assertEquals(logradouro1, paciente.getEndereco().getLogradouro());
		assertEquals(bairro1, paciente.getEndereco().getBairro());
		assertEquals(cep1, paciente.getEndereco().getCep());
		assertEquals(cidade1, paciente.getEndereco().getCidade());
		assertEquals(uf1, paciente.getEndereco().getUf());
		assertEquals(complemento1, paciente.getEndereco().getComplemento());
		assertEquals(numero2, paciente.getEndereco().getNumero());
		
	}

}
