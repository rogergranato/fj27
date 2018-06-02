package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class JPAConfiguration {
	
	@Bean(name = "meuEMMaroto")
	
	public LocalContainerEntityManagerFactoryBean entyMgrFactoryBean()
	{
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		HibernateJpaVendorAdapter hibernate = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(hibernate);
		//em.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
		
		//em.setPackagesToScan("...hard-coded string ...");
		em.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		em.setJpaProperties(jpaProperties());
		return em;
	}
	
	private Properties jpaProperties() {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", Boolean.TRUE.toString());// show sql
		
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.Mysql5InnoDb"); //dialeto
		props.setProperty("hibernate.hbm2ddl", "update"); // mode
		
		return props;
	}

	// o equivalente do persistence.xml
	
	// configura o data source
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setUrl("jdbc://mysql://localhost:3306/casadocodigo");
		dataSource.setPassword("caelum");
		dataSource.setDriverClassName("org.mysql.jdbc.Driver");
		return dataSource;
	}
	
	

}
