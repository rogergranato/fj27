package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Preco {

	public Preco(){}
	public Preco(BigDecimal valor, TipoLivro tipo) {
		this.valor = valor;
		this.tipo = tipo;
	}
	@Column(scale = 2)
	private BigDecimal valor;
	@Column
	@Enumerated(EnumType.STRING)
	private TipoLivro tipo;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoLivro getTipo() {
		return tipo;
	}
	public void setTipo(TipoLivro tipo) {
		this.tipo = tipo;
	}
}
