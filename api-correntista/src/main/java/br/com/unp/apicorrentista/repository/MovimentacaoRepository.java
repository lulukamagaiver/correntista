package br.com.unp.apicorrentista.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unp.apicorrentista.model.Movimentacao;
import br.com.unp.apicorrentista.repository.helper.MovimentacaoHelper;



@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>,MovimentacaoHelper {

	

}

