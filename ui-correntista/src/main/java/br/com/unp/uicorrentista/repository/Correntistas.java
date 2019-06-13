package br.com.unp.uicorrentista.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.unp.uicorrentista.model.Correntista;

@Repository
public class Correntistas {
	
	private static final List<Correntista> LISTA_CORRENTISTAS = new ArrayList<Correntista>();
	
	static {
		LISTA_CORRENTISTAS.add(new Correntista(1l, "Luciene Bezerra", new Date(), new BigDecimal("20000")));
		LISTA_CORRENTISTAS.add(new Correntista(1l, "Marcus Vinicius", new Date(), new BigDecimal("50000")));
	}
	
	public List<Correntista> todos(){
		return Correntistas.LISTA_CORRENTISTAS;
	}
	
	public void adicionar(Correntista correntista) {
		LISTA_CORRENTISTAS.add(correntista);
	}

}
