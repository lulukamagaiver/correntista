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

import br.com.unp.apicorrentista.repository.helper.CorrentistaHelper;
import br.com.unp.apicorrentista.filter.CorrentistaFilter;
import br.com.unp.apicorrentista.model.Correntista;


public class CorrentistaRepositoryImpl implements CorrentistaHelper {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Correntista> filtrar(CorrentistaFilter  correntistaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Correntista> criteria = builder.createQuery(Correntista.class);
		Root<Correntista> root = criteria.from(Correntista.class);
		
		Predicate[] predicates = criarRestrincoes(correntistaFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Correntista> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(correntistaFilter));
	}
	
	
	private Predicate[] criarRestrincoes(CorrentistaFilter  correntistaFilter, CriteriaBuilder builder, Root<Correntista> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (correntistaFilter != null) {
			
                      if (correntistaFilter.getId() != null) {
				predicates.add(builder.equal(root.get("id"), correntistaFilter.getId()));
			}

           if (!StringUtils.isEmpty(correntistaFilter.getNome())) {				
				predicates.add(builder.like(builder.lower(root.get("nome")),"%" + correntistaFilter.getNome().toLowerCase() + "%"));
			}
			

            if (correntistaFilter.getDataCriacao() != null) {
				predicates.add(builder.equal(root.get("dataCriacao"), correntistaFilter.getDataCriacao()));
			}

            if (correntistaFilter.getSaldoFinanceiro() != null) {
				predicates.add(builder.equal(root.get("saldoFinanceiro"), correntistaFilter.getSaldoFinanceiro()));
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
	
	private Long total(CorrentistaFilter  correntistaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Correntista> root = criteria.from(Correntista.class);
		
		Predicate[] predicates = criarRestrincoes(correntistaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
