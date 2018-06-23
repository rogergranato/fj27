package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Usuario pegaUsuarioPeloEmail(String email)
	{
		return em.createQuery("select u from Usuario u where u.email = :email", Usuario.class).setParameter("email", email).getSingleResult();
	}
}
