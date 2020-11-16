package com.example.demo.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
	basePackages = "com.example.demo.repositories.datasource1",
	entityManagerFactoryRef = "firstEntityManagerFactory",
	transactionManagerRef = "firstTransactionManager"
)
public class FirstDatasourceConfig {
	@Autowired
	private Environment env;

	@Bean
	@ConfigurationProperties(prefix="app.datasource.first")
	public DataSourceProperties firstDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource firstDataSource(@Qualifier("firstDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return DataSourceBuilder.create()
			.driverClassName(dataSourceProperties.getDriverClassName())
			.url(dataSourceProperties.getUrl())
			.username(dataSourceProperties.getUsername())
			.password(dataSourceProperties.getPassword())
			.build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(@Qualifier("firstDataSource") DataSource dataSource)
	{
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("com.example.demo.model.datasource1");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
		factory.setJpaProperties(jpaProperties);

		return factory;
	}

	@Bean
	public PlatformTransactionManager firstTransactionManager(@Qualifier("firstEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf)
	{
		return new JpaTransactionManager(Objects.requireNonNull(emf.getObject()));
	}
}
