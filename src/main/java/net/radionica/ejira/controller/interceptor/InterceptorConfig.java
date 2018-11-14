package net.radionica.ejira.controller.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = { "net.radionica.ejira.dao", "net.radionica.ejira.controller.aop" })
@EnableAspectJAutoProxy
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public LogInterceptor interceptor() {
	return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(interceptor()).addPathPatterns("/**").excludePathPatterns("/user/*", "/v2/api-docs",
		"/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");

    }

}
