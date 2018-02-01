package com.gt.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 商城行业版 程序入口
 */
@ServletComponentScan
@SpringBootApplication
public class EducationApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
	return application.sources( EducationApplication.class );
    }

    public static void main( String[] args ) {
	SpringApplication.run( EducationApplication.class, args );
    }
}
