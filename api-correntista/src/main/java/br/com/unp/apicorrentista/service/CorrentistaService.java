package br.com.unp.apicorrentista.service;


import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unp.apicorrentista.model.Correntista;
import br.com.unp.apicorrentista.repository.CorrentistaRepository;
import br.com.unp.apicorrentista.filter.CorrentistaFilter;

@Service
public class CorrentistaService {

	private String errorDelete = "this record is related to other tables.";

    @Autowired
	private CorrentistaRepository correntistaRepository;

	@Transactional
	public Correntista save(Correntista correntista) {
		return correntistaRepository.save(correntista);
	}
	
	public Page<Correntista> filter(CorrentistaFilter correntistaFilter, Pageable pageable) {
		return correntistaRepository.filtrar(correntistaFilter, pageable);
	}

	@Transactional
	public void delete(Long codigo) {
		try {
			correntistaRepository.deleteById(codigo);
		} catch (Exception e) {
			if (e instanceof org.hibernate.exception.ConstraintViolationException
					|| e instanceof DataIntegrityViolationException) {
				throw new IllegalArgumentException(errorDelete);
			}
			throw e;
		}

	}
	
	public Correntista buscarCorrentistaPeloCodigo(Long codigo) {
		Correntista correntistaSalva = correntistaRepository.getOne(codigo);
		if (correntistaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return correntistaSalva;
	}
	
	public Correntista atualizar(Long codigo, Correntista correntista) {
		Correntista correntistaSalva = buscarCorrentistaPeloCodigo(codigo);
		
		BeanUtils.copyProperties(correntista, correntistaSalva, "id");
		return correntistaRepository.save(correntistaSalva);
	}

}
