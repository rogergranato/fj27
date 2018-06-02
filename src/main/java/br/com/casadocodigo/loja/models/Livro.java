package br.com.casadocodigo.loja.models;

public class Livro {

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
}
