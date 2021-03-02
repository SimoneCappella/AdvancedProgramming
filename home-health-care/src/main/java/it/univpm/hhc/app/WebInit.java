package it.univpm.hhc.app;


import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import it.univpm.hhc.security.WebSecurityConfig;

//import it.univpm.hhc.security.WebSecurityConfig;


public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// NB 1st level config class
		return new Class<?>[] { 
			DataServiceConfig.class, 
			WebSecurityConfig.class,
		};
	}

	
	/*@Override
	public void onStartup(ServletContext sc) throws ServletException {
		ServletRegistration.Dynamic appServlet = sc.addServlet("mvc", new DispatcherServlet(
		          new GenericWebApplicationContext()));
        appServlet.setLoadOnStartup(1);
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/WEB-INF/media", (5000000), 5000000, 5000000/2);
		appServlet.setMultipartConfig(multipartConfigElement);
		super.onStartup(sc);
	}*/
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// NB 2nd level config class
		return new Class<?>[] { 
			WebConfig.class,
			//WebSecurityConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		// as the <servlet-mapping>...</servlet-mapping> element in web.xml
		return new String[] { "/Home Health Care" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// as the <filter>...</filter> element in web.xml
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
		return new Filter[] { new HiddenHttpMethodFilter(), cef };
	}

}