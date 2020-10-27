package com.sekolahbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.sekolahbackend.entity.BookCategory;

@Configuration
@EnableJpaRepositories(basePackages = { "com.sekolahbackend.repository" })
public class JpaConfigTest {

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:testdb;sql.syntax_pgs=true");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        return dataSource;
    }

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter hibernateJpa = new HibernateJpaVendorAdapter();
		hibernateJpa.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
		hibernateJpa.setShowSql(true);
		hibernateJpa.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(BookCategory.class.getPackage().getName());
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpa);
		
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		jpaProperties.setProperty("hibernate.ddl-auto", "update");
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return jpaTransactionManager;
	}

}