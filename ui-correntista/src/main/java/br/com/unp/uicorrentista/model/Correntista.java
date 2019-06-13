package br.com.unp.uicorrentista.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "correntista")
public class Correntista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_correntista", insertable=false, updatable = false)
	private Long id;
	private String nome;
	@Column(name = "data_criacao")
	private Date dataCriacao;
	@Column(name = "saldo_financeiro")
	private BigDecimal saldoFinanceiro;
	
	public Correntista() {}

	public Correntista(Long id, String nome, Date dataCriacao, BigDecimal saldoFinanceiro) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.saldoFinanceiro = saldoFinanceiro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getSaldoFinanceiro() {
		return saldoFinanceiro;
	}

	public void setSaldoFinanceiro(BigDecimal saldoFinanceiro) {
		this.saldoFinanceiro = saldoFinanceiro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Correntista other = (Correntista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
