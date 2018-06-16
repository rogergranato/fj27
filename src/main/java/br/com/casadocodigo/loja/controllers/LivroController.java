package br.com.casadocodigo.loja.controllers;


import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.infra.GerenciadorDeArquivo;
import br.com.casadocodigo.loja.models.Livro;
import br.com.casadocodigo.loja.models.TipoLivro;

@Controller
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroDAO livroDAO;
	@Autowired
	private GerenciadorDeArquivo gerenciador;
	
	/* com annotation acima ou sem, mas usando esse contrutor: injeta o livro DAO
	@Autowired
	public LivroController(LivroDAO livroDAO) {
		super();
		this.livroDAO = livroDAO;
	}*/

	// desabilitado. estamos validando usando HibernateValidation
//	@InitBinder
//	public void webdatabinder(WebDataBinder wdb) {
//		wdb.addValidators(new LivroValidator());
//		
//	}
	
//	@RequestMapping("livro/form")
//	public String formulario()
//	{
//		return "livro/form";
//	}

	@RequestMapping("form")
	public ModelAndView formulario(Livro livro)
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
	// a ordem de parametros desse metodo nao pode mudar
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvarLivro(@Valid Livro livro, MultipartFile sumario, BindingResult result, RedirectAttributes attr)
	{
		if (result.hasErrors())
		{
			return formulario(livro);
		}
		
		String path = gerenciador.save("uploads", sumario);
		livro.setCaminhoDoSumario(path);
		livroDAO.save(livro);

		attr.addFlashAttribute("successo", "Livro \"" +  livro.getTitulo() + " \"adicionado com sucesso");
		//return "livro/ok";
		//return listaLivros(); forward (redirect so do lado do servidor). Qdo apertamos F5 a pagina reenvia o form
		return new ModelAndView("redirect:livros"); //similar aa linha de baixo
		//return "redirect:livros";
	}
	
	@RequestMapping("detalhe/{id}")
	public ModelAndView detalheLivro(@PathVariable("id") Long livroId)
	{
		Livro livro = livroDAO.findById(livroId);
		
		if (Objects.isNull(livro))
		{
			return new ModelAndView("404");
		}
		ModelAndView mav = new ModelAndView("livro/detalhe");
		mav.addObject("livro", livro);
		
		return mav;
	}
}
