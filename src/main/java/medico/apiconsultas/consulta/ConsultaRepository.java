package medico.apiconsultas.consulta;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

	boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario,
			LocalDateTime ultimoHorario);
	
	Page<Consulta> findAllByAtivoTrue(Pageable paginacao);


}
