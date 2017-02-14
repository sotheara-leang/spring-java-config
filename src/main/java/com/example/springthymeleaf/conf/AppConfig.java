package com.example.springthymeleaf.conf;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// mark this class as configuration class
@Configuration
// enable jpa and set package for searching repository
@EnableJpaRepositories(basePackages = "com.example.springthymeleaf.repository")
// enable transaction management
@EnableTransactionManagement
// define package to search for spring component. here it excludes from web package
@ComponentScan(basePackages = "com.example.springthymeleaf", excludeFilters = {
		@Filter(type = FilterType.REGEX, pattern = "com.example.springthymeleaf.web..*") })
public class AppConfig {

	/**
	 * Create message source bean for i18n
	 * @return
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		// set folder of messages
		resource.setBasename("/WEB-INF/i18n/messages");
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}

	/**
	 * Create embedded data base with init schema and data
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:sql/create-schema.sql")
				.addScript("classpath:sql/init-data.sql")
				.build();
		return db;
	}

	/**
	 * Create entity manager factory
	 * @return
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		// choose hibernate as implementation
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		// define package to scan for domain
		factory.setPackagesToScan("com.example.springthymeleaf.domain");
		// set persistence unit name as defined in persistent.xml
		factory.setPersistenceUnitName("demo-pu");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	/**
	 * Create transaction manager for handling transaction
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}
}
