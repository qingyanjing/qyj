package org.wisdom.pms.bp.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @date 2018-03-06
 * @author huangpeng
 *
 */
@SuppressWarnings("deprecation")
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter{
	@Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowedHeaders("*")
                .allowCredentials(true)  
                .allowedMethods("*")  
                .maxAge(3600);  
    }  
}
