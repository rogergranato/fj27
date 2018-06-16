package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

// diz para o hibernate
@Entity
public class Livro {

	@Id
	// UUID nao precisa dessa annotation
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//usar uuid para nao ser sequencial
	
	@NotBlank(message="livro.titulo")
	private String titulo;
	
	@NotBlank
	private String autor;
	
	@Lob
	@NotBlank
	private String descricao;
	
	@Min(1)
	@Max(1000)
	private int numPaginas;
	
	//@OneToMany // cria 2 entidades separadas 2ids separados
	@ElementCollection //mesmo id
	private List<Preco> precos;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private Calendar dataLancamento;
	
	@NotBlank
	private String caminhoDoSumario;
	
	public List<Preco> getPrecos() {
		return precos;
	}
	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return titulo + ":" + autor + ":" + numPaginas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
		//UUID.randomUUID();
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getCaminhoDoSumario() {
		return caminhoDoSumario;
	}
	public void setCaminhoDoSumario(String caminhoDoSumario) {
		this.caminhoDoSumario = caminhoDoSumario;
	}
	public BigDecimal priceFor(TipoLivro tipoLivro) {
		// TODO Auto-generated method stub
//		for (Preco preco: precos)
//		{
//			if (preco.getTipo().equals(tipoLivro))
//			{
//				return preco.getValor();
//			}
//		}
		
		//precos.forEach(preco -> {if (preco.getTipo().equals(tipoLivro)){} ...});
		return precos.stream()
				     .filter(preco -> preco.getTipo().equals(tipoLivro))
				     .findFirst()
				     .get()
				     .getValor();
	}
}
