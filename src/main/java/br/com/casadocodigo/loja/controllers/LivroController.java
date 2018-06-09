package br.com.casadocodigo.loja.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Livro;
import br.com.casadocodigo.loja.models.TipoLivro;

@Controller
@RequestMapping("/livros")
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

	@RequestMapping("form")
	public ModelAndView formulario()
	{
		ModelAndView mav = new ModelAndView("livro/form");
		mav.addObject("tiposLivro", TipoLivro.values());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listaLivros()
	{
		ModelAndView mav = new ModelAndView("livro/lista");
		mav.addObject("livros", livroDAO.lista());
		return mav;
	}
	
	@Transactional
	//public String salvarLivro(String titulo, String autor, Integer numPaginas)
	@RequestMapping(method = RequestMethod.POST)
	public String salvarLivro(Livro livro, RedirectAttributes attr)
	{
		System.out.println(livro);
		livroDAO.save(livro);

		attr.addFlashAttribute("successo", "Livro \"" +  livro.getTitulo() + " \"adicionado com sucesso");
		//return "livro/ok";
		//return listaLivros(); forward (redirect so do lado do servidor). Qdo apertamos F5 a pagina reenvia o form
		// return new ModelAndView("redirect:livros"); similar aa linha de baixo
		return "redirect:livros";
	}
}
