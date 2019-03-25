package springMvcTheory.webmvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @author 作者：huangpeng E-mail: 
* @date 创建时间：2019年3月14日 下午3:38:44 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HPAutowired {
	String value() default "";
}
