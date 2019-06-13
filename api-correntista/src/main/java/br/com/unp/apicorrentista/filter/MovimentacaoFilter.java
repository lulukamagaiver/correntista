package br.com.unp.apicorrentista.filter;

import java.util.Date;
import java.math.BigDecimal;
import br.com.unp.apicorrentista.model.Correntista;


public class MovimentacaoFilter {

private Long id;
private String tipoMovimentacao;
private Date dataCriacao;
private BigDecimal valor;
private Correntista correntista;


public Long getId() {
  return this.id;
}
public void setId(Long id) {
  this.id = id;
}
public String getTipoMovimentacao() {
  return this.tipoMovimentacao;
}
public void setTipoMovimentacao(String tipoMovimentacao) {
  this.tipoMovimentacao = tipoMovimentacao;
}
public Date getDataCriacao() {
  return this.dataCriacao;
}
public void setDataCriacao(Date dataCriacao) {
  this.dataCriacao = dataCriacao;
}
public BigDecimal getValor() {
  return this.valor;
}
public void setValor(BigDecimal valor) {
  this.valor = valor;
}
public Correntista getCorrentista() {
  return this.correntista;
}
public void setCorrentista(Correntista correntista) {
  this.correntista = correntista;
}



}
