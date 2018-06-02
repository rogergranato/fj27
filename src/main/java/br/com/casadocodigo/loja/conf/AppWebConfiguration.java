package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;

// Habilita o component MVC do Spring
@EnableWebMvc
// Fala pro Spring que pacotes ele deve ler
@ComponentScan(basePackageClasses={HomeController.class})
//@ComponentScan(basePackages={"br.com.casadocodigo.loja.controllers"})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setSuffix(".jsp");
		resolver.setPrefix("/WEB-INF/views/");
		return resolver;
	}

}
