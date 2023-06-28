package medico.apiconsultas.paciente;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import medico.apiconsultas.endereco.Endereco;

@DataJpaTest
class PacienteRepositoryTest {
	
	@Autowired
	private PacienteRepository pacienteRepository;

	@Test
	@DisplayName("Deve retornar só os pacientes ativos")
	void testFindAllByAtivoTruePaciente() {
		
		var endereco1 = new Endereco("rua santos", "santa maria", "12322277", "22", "bloco B", "Rio de Janeiro", "RJ");
		var endereco2 = new Endereco("rua cananeia", "santa paula", "12320147", "21", "bloco C", "Bahia", "BA");
		var endereco3 = new Endereco("rua orfanato", "vila formosa", "32120147", "24", "bloco D", "São Paulo", "SP");
		
		Paciente paciente1 = new Paciente(null, "Eliana", "eliana@voll.med", "965410298", "411.528.360-74", endereco1, true);
		Paciente paciente2 = new Paciente(null, "Eduardo", "eduardo@voll.med","95207844", "744.214.987-20", endereco2, true);
		
		pacienteRepository.save(paciente1);
		pacienteRepository.save(paciente2);
		
		Paciente pacienteInativo = new Paciente(null, "Liane", "liane@voll.med","95244844", "744.244.987-30", endereco3, false);
		
		pacienteRepository.save(pacienteInativo);
		
		Page<Paciente> pacientesAtivos = pacienteRepository.findAllByAtivoTrue(PageRequest.of(0, 10));
		
		assertEquals(2, pacientesAtivos.getTotalElements());
		
		 List<Paciente> pacientesList = pacientesAtivos.getContent();
		 
		 assertEquals("Eliana", pacientesList.get(0).getNome());
		 assertEquals("Eduardo", pacientesList.get(1).getNome());
		 
		 assertFalse(pacientesList.contains(pacienteInativo));
		
	}

}
