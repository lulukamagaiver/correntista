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
import br.com.unp.apicorrentista.filter.MovimentacaoFilter;
import br.com.unp.apicorrentista.model.Movimentacao;
import br.com.unp.apicorrentista.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoResource {
	
	@Autowired
	private MovimentacaoService movimentacaoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	/**
	 * Pesquisar Movimentacao
	 */
	@GetMapping
	public Page<Movimentacao> search(MovimentacaoFilter movimentacaoFilter, Pageable pageable) {
		return movimentacaoService.filter(movimentacaoFilter, pageable);
	}
	
	/**
	 * Buscar pelo codigo o Movimentacao
	 */
	@GetMapping("/{code}")
	public ResponseEntity<Movimentacao> buscarPeloCodigo(@PathVariable Long code) {
		Movimentacao movimentacao = movimentacaoService.buscarMovimentacaoPeloCodigo(code);
		return movimentacao != null ? ResponseEntity.ok(movimentacao) : ResponseEntity.notFound().build();
	}
	
	/**
	 * Criar Movimentacao
	 */
	@PostMapping
	public ResponseEntity<Movimentacao> criar(@Valid @RequestBody Movimentacao movimentacao, HttpServletResponse response) {
		Movimentacao movimentacaoSalvo = movimentacaoService.save(movimentacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, movimentacaoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoSalvo);
	}
	
	/**
	 * Atualizar Movimentacao
	 */
	@PutMapping("/{code}")
	public ResponseEntity<Movimentacao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Movimentacao movimentacao) {
		Movimentacao movimentacaoSalva = movimentacaoService.atualizar(codigo, movimentacao);
		return ResponseEntity.ok(movimentacaoSalva);
	}
	

	/**
	 * Deletar Movimentacao
	 */
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long code) {
		movimentacaoService.delete(code);
	}

}
