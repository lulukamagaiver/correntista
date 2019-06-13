package br.com.unp.apicorrentista.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import br.com.unp.apicorrentista.filter.CorrentistaFilter;
import br.com.unp.apicorrentista.model.Correntista;



public interface CorrentistaHelper {

	public Page<Correntista> filtrar(CorrentistaFilter correntistaFilter, Pageable pageable);

}
