package br.com.casadocodigo.loja.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.bookpayment.ContratoDoMeuSistemaDePagamento;
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
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.POST)
	public String adicionaItemNoCarrinho(Long livroId, TipoLivro tipoLivro)
	{
      carrinho.add(createItem(livroId, tipoLivro));	
	  return "redirect:/livros";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String finalizarCompra()
	{
	  return "carrinho/checkout";
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public Callable<ModelAndView> realizaCheckout(RedirectAttributes attr)
	{
	  return () -> {
		BigDecimal valorTotal = carrinho.getTotal();
		
		String urlDoSystemaDePagamento = "http://book-payment.herokuapp.com/payment";
		ContratoDoMeuSistemaDePagamento contrato = new ContratoDoMeuSistemaDePagamento(valorTotal);
		
		try{
          // converte o contrato direto para JSON (REST Template); - usa o JACKSON para parsear(ver dependencias)
          // se quiser mudar para XML, alterar o APPWebConfiguration
	      ResponseEntity<String> response = restTemplate.postForEntity(urlDoSystemaDePagamento, contrato, String.class);
	      
	      HttpStatus rc = response.getStatusCode();
	      
	      if (rc.value() == 200)
	      {
	    	  System.out.println("pagamento efetuado com sucesso");
	    	  attr.addFlashAttribute("sucesso", "pagamento efetuado com sucesso");
	    	  carrinho.clean();
	    	  return new ModelAndView("redirect:/livros");
	      }
	      else
	      {
	    	  System.out.println("pagamento nao efetuado");
	    	  attr.addFlashAttribute("falhaPagamento", "pagamento nao efetuado");
	    	  return new ModelAndView("redirect:/shopping");
	      }
		}
		catch (RestClientException e)
		{
			throw new RuntimeException(e);
		}
	  };
	}
	
	
	private ShoppingItem createItem(Long livroId, TipoLivro tipoLivro)
	{
		Livro livro = livroDAO.findById(livroId);
		return new ShoppingItem(livro, tipoLivro);
	}

}
