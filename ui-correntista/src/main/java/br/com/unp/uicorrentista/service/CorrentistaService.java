package br.com.unp.uicorrentista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unp.uicorrentista.model.Correntista;
import br.com.unp.uicorrentista.repository.CorrentistasRepository;

@Service
public class CorrentistaService {
	
	@Autowired
	private CorrentistasRepository repository;
	
	public List<Correntista> findAll() {
		return repository.findAll();
	}
	
	public Optional<Correntista> findOne(Long id) {
		return repository.findById(id);
	}
	
	public Correntista save(Correntista correntista) {
		return repository.saveAndFlush(correntista);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
