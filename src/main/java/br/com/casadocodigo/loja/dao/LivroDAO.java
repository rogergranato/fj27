package br.com.casadocodigo.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import br.com.casadocodigo.loja.models.Livro;

public class LivroDAO {

	@PersistenceContext
	// nao eh necessario...soh temos um
	@Qualifier(value = "meuEMMaroto")
	private EntityManager em;
	
	public Livro save(Livro livro) {
		// acesso o banco e salva
		
		// preciso criar um entity manager (injetado)
		em.persist(livro);
		
		return livro;
		
	}

}
