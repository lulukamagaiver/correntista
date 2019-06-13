package br.com.unp.apicorrentista.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.unp.apicorrentista.event.RecursoCriadoEvent;
import br.com.unp.apicorrentista.filter.CorrentistaFilter;
import br.com.unp.apicorrentista.model.Correntista;
import br.com.unp.apicorrentista.service.CorrentistaService;


@RestController
@RequestMapping("/correntista")
public class CorrentistaResource {
	
	@Autowired
	private CorrentistaService correntistaService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	/**
	 * Pesquisar Correntista
	 */
	@GetMapping
	public Page<Correntista> search(CorrentistaFilter correntistaFilter, Pageable pageable) {
		return correntistaService.filter(correntistaFilter, pageable);
	}
	
	/**
	 * Buscar pelo codigo o Correntista
	 */
	@GetMapping("/{code}")
	public ResponseEntity<Correntista> buscarPeloCodigo(@PathVariable Long code) {
		Correntista correntista = correntistaService.buscarCorrentistaPeloCodigo(code);
		return correntista != null ? ResponseEntity.ok(correntista) : ResponseEntity.notFound().build();
	}
	
	/**
	 * Criar Correntista
	 */
	@PostMapping
	public ResponseEntity<Correntista> criar(@Valid @RequestBody Correntista correntista, HttpServletResponse response) {
		Correntista correntistaSalvo = correntistaService.save(correntista);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, correntistaSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(correntistaSalvo);
	}
	
	/**
	 * Atualizar Correntista
	 */
	@PutMapping("/{code}")
	public ResponseEntity<Correntista> atualizar(@PathVariable Long codigo, @Valid @RequestBody Correntista correntista) {
		Correntista correntistaSalva = correntistaService.atualizar(codigo, correntista);
		return ResponseEntity.ok(correntistaSalva);
	}
	

	/**
	 * Deletar Correntista
	 */
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long code) {
		correntistaService.delete(code);
	}

}
