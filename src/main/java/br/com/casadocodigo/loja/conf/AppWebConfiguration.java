package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.LivroDAO;

// Habilita o component MVC do Spring
@EnableWebMvc
// Fala pro Spring que pacotes ele deve ler
@ComponentScan(basePackageClasses={HomeController.class, LivroDAO.class})
//@ComponentScan(basePackages={"br.com.casadocodigo.loja.controllers"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver internalResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setSuffix(".jsp");
		resolver.setPrefix("/WEB-INF/views/");
		return resolver;
	}
	
	//habilita o WEB-INF resources
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		super.configureDefaultServletHandling(configurer);
		configurer.enable();
	}
}
