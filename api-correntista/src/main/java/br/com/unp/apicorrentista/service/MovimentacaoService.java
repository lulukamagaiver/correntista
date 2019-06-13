package br.com.unp.apicorrentista.service;


import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unp.apicorrentista.model.Movimentacao;
import br.com.unp.apicorrentista.repository.MovimentacaoRepository;
import br.com.unp.apicorrentista.filter.MovimentacaoFilter;

@Service
public class MovimentacaoService {

	private String errorDelete = "this record is related to other tables.";

    @Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Transactional
	public Movimentacao save(Movimentacao movimentacao) {
		return movimentacaoRepository.save(movimentacao);
	}
	
	public Page<Movimentacao> filter(MovimentacaoFilter movimentacaoFilter, Pageable pageable) {
		return movimentacaoRepository.filtrar(movimentacaoFilter, pageable);
	}

	@Transactional
	public void delete(Long codigo) {
		try {
			movimentacaoRepository.deleteById(codigo);
		} catch (Exception e) {
			if (e instanceof org.hibernate.exception.ConstraintViolationException
					|| e instanceof DataIntegrityViolationException) {
				throw new IllegalArgumentException(errorDelete);
			}
			throw e;
		}

	}
	
	public Movimentacao buscarMovimentacaoPeloCodigo(Long codigo) {
		Movimentacao movimentacaoSalva = movimentacaoRepository.getOne(codigo);
		if (movimentacaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return movimentacaoSalva;
	}
	
	public Movimentacao atualizar(Long codigo, Movimentacao movimentacao) {
		Movimentacao movimentacaoSalva = buscarMovimentacaoPeloCodigo(codigo);
		
		BeanUtils.copyProperties(movimentacao, movimentacaoSalva, "id");
		return movimentacaoRepository.save(movimentacaoSalva);
	}

}
