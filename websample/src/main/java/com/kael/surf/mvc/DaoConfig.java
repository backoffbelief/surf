package com.kael.surf.mvc;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
@Configuration
@Import({DataSourceConfig.class})
public class DaoConfig {
	   
    @Value("${hibernate.dialect}")  
    String hibernate_dialect;  
    @Value("${hibernate.show_sql}")  
    String hibernate_show_sql;  
      
    /**                                                           
    * 描述 : <负责解析资源文件>. <br>  
    *<p>  
        <这个类必须有，而且必须声明为static，否则不能正常解析>   
     </p>                                                                                                                                                                                                                                                 
    * @return                                                                                                       
    */    
    @Bean  
    public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {  
        //logger.info("PropertySourcesPlaceholderConfigurer");  
        return new PropertySourcesPlaceholderConfigurer();  
    }  
      
    @Autowired  
    public DataSource dataSource;  
  
    @Bean(name = "sessionFactory")  
    public LocalSessionFactoryBean localSessionFactoryBean() {  
       // logger.info("sessionFactory");  
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();  
        sessionFactory.setDataSource(dataSource);  
        String[] packagesToScan = new String[] { "web.function.**.model.oracle" };  
        sessionFactory.setPackagesToScan(packagesToScan);  
  
        Properties hibernateProperties = new Properties();  
        hibernateProperties.setProperty("hibernate.dialect", hibernate_dialect);  
        hibernateProperties.setProperty("hibernate.show_sql",  
                hibernate_show_sql);  
        hibernateProperties.setProperty(  
                "hibernate.current_session_context_class",  
                "org.springframework.orm.hibernate4.SpringSessionContext");  
        sessionFactory.setHibernateProperties(hibernateProperties);  
  
        return sessionFactory;  
  
    }  
  
//    @Bean(name = "hibernateDAO")  
//    public CP_Hibernate4DAOImpl hibernate4Dao() {  
//       // logger.info("hibernateDAO");  
//        CP_Hibernate4DAOImpl dao = new CP_Hibernate4DAOImpl();  
//        dao.setSessionFactory(localSessionFactoryBean().getObject());  
//        return dao;  
//    }  
  
    @Bean(name = "transactionManager")  
    public HibernateTransactionManager hibernateTransactionManager() {  
        //logger.info("transactionManager");  
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();  
//        hibernateTransactionManager.setSessionFactory(localSessionFactoryBean()  
//                .getObject());  
        return hibernateTransactionManager;  
    }  
}
