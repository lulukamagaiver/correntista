package br.com.unp.uicorrentista.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unp.uicorrentista.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

}
