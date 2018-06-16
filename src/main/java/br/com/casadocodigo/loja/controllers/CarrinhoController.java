package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Livro;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;
import br.com.casadocodigo.loja.models.TipoLivro;

@Controller
@RequestMapping("/shopping")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
	
	@Autowired
	private ShoppingCart carrinho;
	@Autowired
	private LivroDAO livroDAO;
	
	@RequestMapping(method=RequestMethod.POST)
	public String adicionaItemNoCarrinho(Long livroId, TipoLivro tipoLivro)
	{
      carrinho.add(createItem(livroId, tipoLivro));	
	  return "redirect:/livros";
	}
	
	private ShoppingItem createItem(Long livroId, TipoLivro tipoLivro)
	{
		Livro livro = livroDAO.findById(livroId);
		return new ShoppingItem(livro, tipoLivro);
	}

}
