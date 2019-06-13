package br.com.unp.apicorrentista.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.unp.apicorrentista.repository.helper.MovimentacaoHelper;
import br.com.unp.apicorrentista.filter.MovimentacaoFilter;
import br.com.unp.apicorrentista.model.Movimentacao;


public class MovimentacaoRepositoryImpl implements MovimentacaoHelper {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Movimentacao> filtrar(MovimentacaoFilter  movimentacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> criteria = builder.createQuery(Movimentacao.class);
		Root<Movimentacao> root = criteria.from(Movimentacao.class);
		
		Predicate[] predicates = criarRestrincoes(movimentacaoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Movimentacao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(movimentacaoFilter));
	}
	
	
	private Predicate[] criarRestrincoes(MovimentacaoFilter  movimentacaoFilter, CriteriaBuilder builder, Root<Movimentacao> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (movimentacaoFilter != null) {
			
                      if (movimentacaoFilter.getId() != null) {
				predicates.add(builder.equal(root.get("id"), movimentacaoFilter.getId()));
			}

           if (!StringUtils.isEmpty(movimentacaoFilter.getTipoMovimentacao())) {				
				predicates.add(builder.like(builder.lower(root.get("tipoMovimentacao")),"%" + movimentacaoFilter.getTipoMovimentacao().toLowerCase() + "%"));
			}
			

            if (movimentacaoFilter.getDataCriacao() != null) {
				predicates.add(builder.equal(root.get("dataCriacao"), movimentacaoFilter.getDataCriacao()));
			}

            if (movimentacaoFilter.getValor() != null) {
				predicates.add(builder.equal(root.get("valor"), movimentacaoFilter.getValor()));
			}

            if (movimentacaoFilter.getCorrentista() != null) {
				predicates.add(builder.equal(root.get("correntista"), movimentacaoFilter.getCorrentista()));
			}



		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(MovimentacaoFilter  movimentacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Movimentacao> root = criteria.from(Movimentacao.class);
		
		Predicate[] predicates = criarRestrincoes(movimentacaoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
