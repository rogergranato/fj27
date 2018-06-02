package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("home")
	public String home()
	{
		return "home";
	}
/*
	@RequestMapping("/home1")
	public void index() {
		System.out.println("Carregando os produtos 1");
		//retorna 404 mas escreve no console
	}
	
	@RequestMapping(value = "/home2")
	public void index2() {
		System.out.println("Carregando os produtos 2");
	}
	
	@RequestMapping("home3")
	public void index3() {
		System.out.println("Carregando os produtos 3");
	}*/
}
