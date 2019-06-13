package br.com.unp.apicorrentista.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import br.com.unp.apicorrentista.filter.MovimentacaoFilter;
import br.com.unp.apicorrentista.model.Movimentacao;



public interface MovimentacaoHelper {

	public Page<Movimentacao> filtrar(MovimentacaoFilter movimentacaoFilter, Pageable pageable);

}
