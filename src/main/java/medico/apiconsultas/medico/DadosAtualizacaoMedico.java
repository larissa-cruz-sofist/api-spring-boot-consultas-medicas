package medico.apiconsultas.medico;

import jakarta.validation.constraints.NotNull;
import medico.apiconsultas.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
		
		@NotNull
		Long id, 
		
		String nome, 
		
		String telefone, 
		
		DadosEndereco endereco) {

}
