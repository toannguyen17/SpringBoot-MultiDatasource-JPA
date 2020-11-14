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
	basePackages = "com.example.demo.repositories.datasource2",
	entityManagerFactoryRef = "secondEntityManagerFactory",
	transactionManagerRef = "secondTransactionManager"
)
public class SecondDatasourceConfig {
	@Autowired
	private Environment env;

	@Bean("datasource-second-p")
	@ConfigurationProperties(prefix="app.datasource.second")
	public DataSourceProperties secondDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean("datasource-second")
	public DataSource secondDataSource(@Qualifier("datasource-second-p") DataSourceProperties dataSourceProperties) {
		return DataSourceBuilder.create()
			.driverClassName(dataSourceProperties.getDriverClassName())
			.url(dataSourceProperties.getUrl())
			.username(dataSourceProperties.getUsername())
			.password(dataSourceProperties.getPassword())
			.build();
	}

	@Bean
	public PlatformTransactionManager secondTransactionManager(@Qualifier("second-emf") LocalContainerEntityManagerFactoryBean emf)
	{
		return new JpaTransactionManager(Objects.requireNonNull(emf.getObject()));
	}

	@Bean("second-emf")
	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(@Qualifier("datasource-second") DataSource dataSource)
	{
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("com.example.demo.model.datasource2");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
		factory.setJpaProperties(jpaProperties);

		return factory;
	}
}
