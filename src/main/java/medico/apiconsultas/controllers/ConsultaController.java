package medico.apiconsultas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medico.apiconsultas.consulta.AgendaDeConsultas;
import medico.apiconsultas.consulta.ConsultaRepository;
import medico.apiconsultas.consulta.DadosAgendamentoConsulta;
import medico.apiconsultas.consulta.DadosDetalhamentoConsulta;
import medico.apiconsultas.consulta.DadosListagemConsulta;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
	
	@Autowired
	private AgendaDeConsultas agenda;
	
	@Autowired
	private ConsultaRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
		var dto = agenda.agendar(dados);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemConsulta>> listar(@PageableDefault(size = 10) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemConsulta::new);
		return ResponseEntity.ok(page);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		var consulta = repository.getReferenceById(id);
		consulta.excluir();
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoConsulta> detalhar(@PathVariable Long id) {
		var consulta = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
	}
	
}
