package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority{

	@Id
	private String nome;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return nome;
	}

}
