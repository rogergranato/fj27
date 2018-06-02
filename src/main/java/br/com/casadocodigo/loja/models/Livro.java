package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


// diz para o hibernate
@Entity
public class Livro {

	@Id
	// UUID nao precisa dessa annotation
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//usar uuid para nao ser sequencial
	
	private String titulo;
	private String autor;
	private String numPaginas;
	public String getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(String numPaginas) {
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
}
