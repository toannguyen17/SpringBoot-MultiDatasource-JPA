package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Bean
	public OpenEntityManagerInViewFilter firstOpenEntityManagerInViewFilter()
	{
		OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
		osivFilter.setEntityManagerFactoryBeanName("firstEntityManagerFactory");
		return osivFilter;
	}

	@Bean
	public OpenEntityManagerInViewFilter secondOpenEntityManagerInViewFilter()
	{
		OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
		osivFilter.setEntityManagerFactoryBeanName("secondEntityManagerFactory");
		return osivFilter;
	}
}
