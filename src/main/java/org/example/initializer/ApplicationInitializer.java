package org.example.initializer;

import org.example.configuration.ApplicationConfiguration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[0];
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{ApplicationConfiguration.class};
  }

  @Override
  @NonNull
  protected String[] getServletMappings() {
    return new String[]{"/*"};
  }
}
