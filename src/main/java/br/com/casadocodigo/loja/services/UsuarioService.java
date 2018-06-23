package br.com.casadocodigo.loja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.casadocodigo.loja.daos.UsuarioDAO;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired UsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		/*Usuario usuario = usuarioDAO.pegaUsuarioPeloEmail(name);
		
		if (Objects.isNull(usuario))
		{
			new UsernameNotFoundException("Usuario: " + name + " nao encontrado.");
		}
		
		return usuario;*/
		
		// java 8
		return Optional.ofNullable(usuarioDAO.pegaUsuarioPeloEmail(name)).
				        orElseThrow(() -> new UsernameNotFoundException("Usuario: " + name + " nao encontrado."));
	}

}
