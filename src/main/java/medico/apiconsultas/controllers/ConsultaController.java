package medico.apiconsultas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medico.apiconsultas.consulta.AgendaDeConsultas;
import medico.apiconsultas.consulta.DadosAgendamentoConsulta;
import medico.apiconsultas.consulta.DadosDetalhamentoConsulta;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
	
	@Autowired
	private AgendaDeConsultas agenda;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
		var dto = agenda.agendar(dados);
		return ResponseEntity.ok(dto);
	}
	
}
