package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@ComponentScan("org.example")
public class ApplicationConfiguration {
  @Bean
  public DataSource dataSource() throws NamingException {
    return new JndiTemplate().lookup("java:comp/env/jdbc/db", DataSource.class);
  }
}
