package medico.apiconsultas.medico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import medico.apiconsultas.endereco.*;
import medico.apiconsultas.consulta.*;
import medico.apiconsultas.paciente.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.temporal.TemporalAdjusters;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@DataJpaTest
class MedicoRepositoryTest {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario1() {
		//given
		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		
		//when
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10, true);
		
        //then ou assert
		var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
		assertThat(medicoLivre).isNull();
	}
	
	@Test
	@DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario2() {
		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
     
		var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
		assertThat(medicoLivre).isEqualTo(medico);
	}
	
	
	 private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data, Boolean ativo) {
	        em.persist(new Consulta(null, medico, paciente, data, ativo));
	    }

	    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
	        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
	        em.persist(medico);
	        return medico;
	    }

	    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
	        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
	        em.persist(paciente);
	        return paciente;
	    }

	    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
	        return new DadosCadastroMedico(
	                nome,
	                email,
	                "61999999999",
	                crm,
	                especialidade,
	                dadosEndereco()
	        );
	    }

	    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
	        return new DadosCadastroPaciente(
	                nome,
	                email,
	                "61999999999",
	                cpf,
	                dadosEndereco()
	        );
	    }

	
    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
    
	@Test
	@DisplayName("Deve retornar só os pacientes ativos")
	void testFindAllByAtivoTrue_medico() {
		
		var endereco1 = new Endereco("rua andreas", "sao jose", "19999977", "2288", "bloco A", "Bahia", "BA");
		var endereco2 = new Endereco("rua canada", "sao pedro", "12388888", "2100", "bloco F", "Rio de Janeiro", "RJ");
		var endereco3 = new Endereco("rua alfredo", "sao rafael", "32122222", "2474", "bloco G", "São Paulo", "SP");
		
		var especialidade1 = Especialidade.CARDIOLOGIA;
		var especialidade2 = Especialidade.ORTOPEDIA;
		var especialidade3 = Especialidade.GINECOLOGIA;
		
		Medico medico1 = new Medico(null, "Carlos", "carlos@voll.med", "965412298", "801118", especialidade1, endereco1, true);
		Medico medico2 = new Medico(null, "Georgia", "georgia@voll.med","95211144", "800008", especialidade2, endereco2, true);
		
		medicoRepository.save(medico1);
		medicoRepository.save(medico2);
		
		Medico pacienteInativo = new Medico(null, "Elisa", "elisa@voll.med","00004844", "521045", especialidade3, endereco3, false);
		
		medicoRepository.save(pacienteInativo);
		
		Page<Medico> medicosAtivos = medicoRepository.findAllByAtivoTrue(PageRequest.of(0, 10));
		
		assertEquals(2, medicosAtivos.getTotalElements());
		
		
	}

}
