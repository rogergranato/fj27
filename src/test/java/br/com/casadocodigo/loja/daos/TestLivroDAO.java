package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.JPAConfigurationParaTests;
import br.com.casadocodigo.loja.models.Livro;
import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.TipoLivro;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {JPAConfigurationParaTests.class, JPAConfiguration.class, LivroDAO.class})
@ActiveProfiles("test")
public class TestLivroDAO {
	
	@Autowired
	LivroDAO dao;
	
	@Test
	@Transactional
	public void devRetornarListaDeLivros()
	{
		Livro livro = new Livro();
		livro.setAutor("Autor Test");
		livro.setTitulo("Livro de Teste");
		livro.setNumPaginas(120);
		livro.setDescricao("Ola");
		
		livro.setPrecos(Arrays.asList(new Preco(BigDecimal.TEN, TipoLivro.AUDIOLIVRO)));
		dao.save(livro);
		
		List<Livro> livros = dao.lista();
		Assert.assertEquals(1, livros.size());
		Assert.assertEquals(livro.getAutor(), livros.get(0).getAutor());
		Assert.assertEquals(livro.getTitulo(), livros.get(0).getTitulo());
		Assert.assertEquals(livro.getDescricao(), livros.get(0).getDescricao());
	}

}
