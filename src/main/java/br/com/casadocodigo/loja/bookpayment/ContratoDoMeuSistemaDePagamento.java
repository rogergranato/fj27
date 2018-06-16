package br.com.casadocodigo.loja.bookpayment;

import java.math.BigDecimal;

public class ContratoDoMeuSistemaDePagamento {

	// mesmo nome do JSON
	private final BigDecimal value;

	public ContratoDoMeuSistemaDePagamento(BigDecimal valorTotalDaCompra) {
		this.value = valorTotalDaCompra;
	}
	
	public BigDecimal getValue() {
		return value;
	}

}
