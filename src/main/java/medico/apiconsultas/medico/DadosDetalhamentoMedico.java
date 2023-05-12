package medico.apiconsultas.medico;

import medico.apiconsultas.endereco.*;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco, Boolean ativo) {

	   public DadosDetalhamentoMedico(Medico medico) {
	        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco(), medico.getAtivo());
	    }
	
}
