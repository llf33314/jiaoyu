package com.gt.education.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 调用url之后的方法
 * @author Administrator
 *
 */
//注解会在class中存在，运行时可通过反射获取
@Retention(RetentionPolicy.RUNTIME)
//目标
@Target(ElementType.METHOD)
//文档生成时，该注解将被包含在javadoc中，可去掉
@Documented
public @interface AfterAnno {
	String style() default "";
	
	String remark() default "";
}
