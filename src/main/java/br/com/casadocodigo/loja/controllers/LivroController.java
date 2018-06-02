package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.models.Livro;

@Controller
public class LivroController {

	@RequestMapping("livro/form")
	public String formulario()
	{
		return "livro/form";
	}
	
	@RequestMapping("livro/salva")
	//public String salvarLivro(String titulo, String autor, Integer numPaginas)
	public String salvarLivro(Livro livro)
	{
		System.out.println(livro);
		return "livro/ok";
	}
}
