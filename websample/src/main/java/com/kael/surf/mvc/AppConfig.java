package com.kael.surf.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.mvc.Controller;

@Configuration
@ComponentScan(basePackages = "com.kael" , excludeFilters ={@ComponentScan.Filter(type= FilterType.ANNOTATION,value = {Controller.class})})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({CachingConfig.class,DaoConfig.class})
public class AppConfig {

}
