package medico.apiconsultas.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data, Boolean ativo) {

	public DadosDetalhamentoConsulta(Consulta consulta) {
		
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getAtivo());
	}

}
