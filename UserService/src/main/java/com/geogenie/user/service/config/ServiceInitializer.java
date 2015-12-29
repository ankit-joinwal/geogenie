package com.geogenie.user.service.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class ServiceInitializer implements WebApplicationInitializer{


	private static final String SECURITY_FILTER_NAME = AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;
    private static final String DISPATCHER_SERVLET_NAME = AbstractDispatcherServletInitializer.DEFAULT_SERVLET_NAME;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext rootContext = createRootContext(servletContext);

        configureSpringMvc(servletContext, rootContext);
    }

    private WebApplicationContext createRootContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfiguration.class);
        context.register(HibernateConfiguration.class);
        context.register(AppSecurityConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));

        return context;
    }

    private void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
        DispatcherServlet dispatcher = new DispatcherServlet(rootContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcher);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");
    }
    
   /* private void addFilters(ServletContext servletContext, WebApplicationContext rootContext){
    	
    	//servletContext.addFilter(filterName, filter)
    }*/
    
    

	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {  
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();  
        dispatcherContext.setConfigLocation("com.geogenie.user.service.config");
        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));  
        dynamic.addMapping("/");  
        dynamic.setLoadOnStartup(1);  
        
   }  */
}
