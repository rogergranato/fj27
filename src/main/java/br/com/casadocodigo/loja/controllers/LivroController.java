package br.com.casadocodigo.loja.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Livro;
import br.com.casadocodigo.loja.models.TipoLivro;

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

//	@RequestMapping("livro/form")
//	public String formulario()
//	{
//		return "livro/form";
//	}

	@RequestMapping("livro/form")
	public ModelAndView formulario()
	{
		ModelAndView view = new ModelAndView(/*viewName: */"livro/form");
		view.addObject(/*attributeName: */"tiposLivros", TipoLivro.values());
		
		return view;
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
