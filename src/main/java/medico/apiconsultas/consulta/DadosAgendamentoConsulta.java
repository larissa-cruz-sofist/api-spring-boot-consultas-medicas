package medico.apiconsultas.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import medico.apiconsultas.medico.Especialidade;

public record DadosAgendamentoConsulta(
		
    Long idMedico,

    @NotNull
    Long idPaciente,

    @NotNull
    @Future
    LocalDateTime data,
    
	Especialidade especialidade) {
	
}
