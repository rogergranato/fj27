package br.com.casadocodigo.loja.conf;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.infra.GerenciadorDeArquivo;

// Habilita o component MVC do Spring
@EnableWebMvc
// Fala pro Spring que pacotes ele deve ler
@ComponentScan(basePackageClasses={HomeController.class, LivroDAO.class, GerenciadorDeArquivo.class})
//@ComponentScan(basePackages={"br.com.casadocodigo.loja.controllers"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver internalResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setSuffix(".jsp");
		resolver.setPrefix("/WEB-INF/views/");
		return resolver;
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource  bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding(StandardCharsets.UTF_8.name());
		bundle.setCacheSeconds(3);
		return bundle;
	}
	
	// o nome desse metodo nao eh customizavel
	@Bean
	public FormattingConversionService mvcConversionService(){
		// ensina o spring a usar o padrao do browser
		DateFormatter df = new DateFormatter("yyyy-MM-dd");
		
		// nao vou criar outro FormattingConversionService. senao perco o que o spring ja define paara mim (usa o default)
		DefaultFormattingConversionService dfcs = new DefaultFormattingConversionService();
		
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(df);
		registrar.registerFormatters(dfcs);
		
		return dfcs;
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		//TODO return the default
		return new StandardServletMultipartResolver();
	}
	
	//habilita o WEB-INF resources
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		super.configureDefaultServletHandling(configurer);
		configurer.enable();
	}
}
