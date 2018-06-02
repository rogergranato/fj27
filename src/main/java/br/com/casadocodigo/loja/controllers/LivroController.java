package br.com.casadocodigo.loja.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Livro;

@Controller
public class LivroController {

	@Autowired
	private LivroDAO livroDAO;
	
	/* com annotation acima ou sem, mas usando esse contrutor: injeta o livro DAO
	@Autowired
	public LivroController(LivroDAO livroDAO) {
		super();
		this.livroDAO = livroDAO;
	}*/

	@RequestMapping("livro/form")
	public String formulario()
	{
		return "livro/form";
	}

	@Transactional
	@RequestMapping("livro/salva")
	//public String salvarLivro(String titulo, String autor, Integer numPaginas)
	public String salvarLivro(Livro livro)
	{
		System.out.println(livro);
		livroDAO.save(livro);
		
		return "livro/ok";
	}
}
