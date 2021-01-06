package it.univpm.hhc.test;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.univpm.hhc.app.DataServiceConfig;

@Configuration
//@ComponentScan(basePackages =
//     "it.univpm.advprog.singers")
//@ComponentScan(basePackages = { "it.univpm.advprog.singers.controller", "it.univpm.advprog.singers.model",
//		"it.univpm.advprog.singers.services" })
//@ComponentScan(basePackages = { "it.univpm.advprog.singers" },
//excludeFilters  = {@ComponentScan.Filter(
//		type = FilterType.ASSIGNABLE_TYPE, classes = {WebConfig.class, WebInit.class, DataServiceConfig.class})})
@ComponentScan(basePackages = { "it.univpm.hhc.model" })
@EnableTransactionManagement
public class DataServiceConfigTest extends DataServiceConfig {

	@Bean
	@Override
	protected Properties hibernateProperties() {
		Properties hibernateProp = super.hibernateProperties();
		hibernateProp.put("javax.persistence.schema-generation.database.action", "drop-and-create");
		return hibernateProp;
	}
}
