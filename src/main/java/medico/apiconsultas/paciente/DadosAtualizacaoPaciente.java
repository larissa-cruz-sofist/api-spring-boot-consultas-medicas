package medico.apiconsultas.paciente;

import jakarta.validation.constraints.NotNull;
import medico.apiconsultas.endereco.*;

public record DadosAtualizacaoPaciente( 

    @NotNull
    Long id,
    
    String nome,
    
    String telefone,
    
    DadosEndereco endereco) {
	
}
