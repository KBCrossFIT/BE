package com.be.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@ComponentScan(basePackages = "com.be")
public class ServletConfig  implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/resources/index.html");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("/resources/assets/");
    }


    //	Servlet 3.0 파일 업로드 사용시
//    @Bean
//    public MultipartResolver multipartResolver() {
//        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
//        return resolver;
//    }

}