package com.kael.surf.mvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.util.Log4jConfigListener;

@Order(1)
public class CommonInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		/**
		 *  <context-param>  
    <param-name>log4jConfigLocation</param-name>  
    <param-value>classpath:config/properties/log4j.properties</param-value>  
       </context-param>  
       <listener>  
    <listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>  
       </listener>  
		 */
		 //Log4jConfigListener  
        servletContext.setInitParameter("log4jConfigLocation", "classpath:config/properties/log4j.properties");  
        servletContext.addListener(Log4jConfigListener.class); 
        
        /**
         *  <servlet>  
    <servlet-name>demoServlet</servlet-name>  
    <servlet-class>web.function.servlet.DemoServlet</servlet-class>  
    <load-on-startup>2</load-on-startup>  
       </servlet>  
<servlet-mapping>  
    <servlet-name>demoServlet</servlet-name>  
    <url-pattern>/demo_servlet</url-pattern>  
</servlet-mapping>  
         */
        //
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("LoginFuncServlet", LoginFuncServlet.class);
        
        dynamic.setLoadOnStartup(2);
        dynamic.addMapping("/LoginFunc");
        
	}

}
