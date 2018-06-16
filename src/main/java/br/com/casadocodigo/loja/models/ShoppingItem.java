package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingItem implements Serializable {

	private Livro livro;
	private TipoLivro tipoLivro;
	private Long livroId;
	
	public ShoppingItem(Livro livro, TipoLivro tipoLivro) {
		this.setLivro(livro);
		this.setTipoLivro(tipoLivro);
		this.setLivroId(livro.getId());
	}
	
	public BigDecimal getPrice() {
		return livro.priceFor(tipoLivro);
	}
	
	public BigDecimal getTotal(Integer quantity) {
		return getPrice().multiply(new BigDecimal(quantity));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tipoLivro == null) ? 0 : tipoLivro.hashCode());
		result = prime * result
				+ ((livro == null) ? 0 : livro.hashCode());
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
		ShoppingItem other = (ShoppingItem) obj;
		if (tipoLivro != other.tipoLivro)
			return false;
		if (livroId == null) {
			if (other.livroId != null)
				return false;
		} else if (!livroId.equals(other.livroId))
			return false;
		return true;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public TipoLivro getTipoLivro() {
		return tipoLivro;
	}

	public void setTipoLivro(TipoLivro tipoLivro) {
		this.tipoLivro = tipoLivro;
	}

	public Long getLivroId() {
		return livroId;
	}

	public void setLivroId(Long livroId) {
		this.livroId = livroId;
	}

}
