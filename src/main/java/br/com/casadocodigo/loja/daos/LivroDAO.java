package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Livro;

//@Component 
// geralmente usa-se para acessar dados - mas pode ser component
@Repository
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

	public List<Livro> lista() {
		// mysql> select * from Livro l inner join Livro_precos lp on l.id=lp.id;
		return em.createQuery("select distinct(l) from Livro l join fetch l.precos", Livro.class).getResultList();
		//return em.createQuery("select l from Livro l", Livro.class).getResultList();
	}

	public Livro findById(Long livroId) {
		// TODO Auto-generated method stub
		return em.createQuery("select distinct(l) from Livro l join fetch l.precos where l.id = :id", Livro.class)
				.setParameter("id", livroId)
				.getSingleResult();
		
	}
}
