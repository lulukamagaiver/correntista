package br.com.unp.uicorrentista.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unp.uicorrentista.model.Correntista;

public interface CorrentistasRepository extends JpaRepository<Correntista, Long> {

}
