package br.com.unp.apicorrentista.filter;

import java.util.Date;
import java.math.BigDecimal;


public class CorrentistaFilter {

private Long id;
private String nome;
private Date dataCriacao;
private BigDecimal saldoFinanceiro;


public Long getId() {
  return this.id;
}
public void setId(Long id) {
  this.id = id;
}
public String getNome() {
  return this.nome;
}
public void setNome(String nome) {
  this.nome = nome;
}
public Date getDataCriacao() {
  return this.dataCriacao;
}
public void setDataCriacao(Date dataCriacao) {
  this.dataCriacao = dataCriacao;
}
public BigDecimal getSaldoFinanceiro() {
  return this.saldoFinanceiro;
}
public void setSaldoFinanceiro(BigDecimal saldoFinanceiro) {
  this.saldoFinanceiro = saldoFinanceiro;
}



}
