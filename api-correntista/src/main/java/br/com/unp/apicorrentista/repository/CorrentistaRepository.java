package br.com.unp.apicorrentista.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unp.apicorrentista.model.Correntista;
import br.com.unp.apicorrentista.repository.helper.CorrentistaHelper;



@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Long>,CorrentistaHelper {

	

}

