package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	// configura o hibernate
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
		
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"); //dialeto
		props.setProperty("hibernate.hbm2ddl.auto", "update"); // mode
		
		return props;
	}

	// o equivalente do persistence.xml
	
	// configura o data source
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo?createIfNotExists=true");
		dataSource.setPassword("");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	// configura o transaction manager
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory em)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(em);
		return transactionManager;
	}

}
