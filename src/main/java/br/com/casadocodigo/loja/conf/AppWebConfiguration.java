package br.com.casadocodigo.loja.conf;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.infra.GerenciadorDeArquivo;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.resolvers.JSONViewResolver;
import br.com.casadocodigo.loja.services.UsuarioService;

// Habilita o component MVC do Spring
@EnableWebMvc
// habilita o caching
@EnableCaching
// Fala pro Spring que pacotes ele deve ler
@ComponentScan(basePackageClasses={HomeController.class, 
                                   LivroDAO.class,
                                   GerenciadorDeArquivo.class,
                                   ShoppingCart.class,
                                   UsuarioService.class})
//@ComponentScan(basePackages={"br.com.casadocodigo.loja.controllers"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver internalResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setSuffix(".jsp");
		resolver.setPrefix("/WEB-INF/views/");
		// todas as classes contorladas pelo spring ficam disponiveis no jsp
		//resolver.setExposeContextBeansAsAttributes(true);
		//expoe ShoppingCart no JSP
		resolver.setExposedContextBeanNames("shoppingCart");
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
		configurer.enable();
	}
	
	@Bean
	public RestTemplate criaRestTemplate()
	{
		return new RestTemplate();
	}
	
	@Bean
	public ViewResolver contentNegViewResolver(ContentNegotiationManager manager)
	{
		List<ViewResolver> resolvers = new ArrayList<>();
		resolvers.add(internalResolver());
		resolvers.add(new JSONViewResolver());
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		
		return resolver;
	}
	
	// habilita nosso cache manager
	@Bean
	public CacheManager createNossoCacheManager()
	{
		// usa o default para usar nossa propria memoria
		//return new ConcurrentMapCacheManager();
		
		// Usando o Guava (GOOGLE)
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
				                                           .maximumSize(100L)
				                                           .expireAfterAccess(10L, TimeUnit.MINUTES);
		GuavaCacheManager mgr = new GuavaCacheManager();
		mgr.setCacheBuilder(builder);
		return mgr;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Bean
	public LocaleResolver localeResolver()
	{
		return new CookieLocaleResolver();
	}
	
}
