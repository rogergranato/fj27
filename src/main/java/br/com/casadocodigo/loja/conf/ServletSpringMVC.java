package br.com.casadocodigo.loja.conf;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.casadocodigo.loja.infra.GerenciadorDeArquivo;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{AppWebConfiguration.class,
						   JPAConfiguration.class,
						   GerenciadorDeArquivo.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		// TODO Auto-generated method stub
		registration.setMultipartConfig(new MultipartConfigElement(""));
		super.customizeRegistration(registration);
	}
}
