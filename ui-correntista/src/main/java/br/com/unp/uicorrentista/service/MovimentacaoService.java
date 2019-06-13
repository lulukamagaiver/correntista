package br.com.unp.uicorrentista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unp.uicorrentista.model.Movimentacao;
import br.com.unp.uicorrentista.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository repository;
	
	public List<Movimentacao> findAll() {
		return repository.findAll();
	}
	
	public Optional<Movimentacao> findOne(Long id) {
		return repository.findById(id);
	}
	
	public Movimentacao save(Movimentacao movimentacao) {
		return repository.saveAndFlush(movimentacao);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
