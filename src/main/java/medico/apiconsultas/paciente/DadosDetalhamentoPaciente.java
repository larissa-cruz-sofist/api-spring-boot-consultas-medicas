package medico.apiconsultas.paciente;

import medico.apiconsultas.endereco.*;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String cpf, String telefone, Endereco endereco, Boolean ativo) {
	
	 public DadosDetalhamentoPaciente(Paciente paciente) {
	        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco(), paciente.getAtivo());
	    }

}
