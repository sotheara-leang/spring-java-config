package com.example.springthymeleaf.conf;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

//mark this class as configuration class
@Configuration
// enable web mvc
@EnableWebMvc
// define package to search for controller...
@ComponentScan("com.example.springthymeleaf.web")
public class MvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Mapping url /resource/** to folder resources of webapp (webapp/resources) as the static resources
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * Register local change interceptor
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/**
	 * Define template resolver for thymeleaf template.
	 * 
	 * @return
	 */
	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		// where to look up view file
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	/**
	 * Define template enginer for thymeleaf
	 * @param tempalteResolver
	 * @param messageSource
	 * @return
	 */
	@Bean
	public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver tempalteResolver,
			MessageSource messageSource) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(tempalteResolver);
		// register message source to solve i18n
		templateEngine.setMessageSource(messageSource);
		// register layout dialect for template by decorate it
		templateEngine.addDialect(new LayoutDialect());
		// register dialect for spring security
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}

	/**
	 * Thymeleaf view resolver
	 * @param templateEngine
	 * @return
	 */
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		// set it as the primary view resolver. it will be handled first
		viewResolver.setOrder(0);
		viewResolver.setCache(false);
		return viewResolver;
	}

	/**
	 * Use cookie for locale resolver
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		// set English as default locale
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		// set max age of cookie
		localeResolver.setCookieMaxAge(86400);
		// set locale cookie name
		localeResolver.setCookieName("lang");
		return localeResolver;
	}

	/**
	 * for intercept locale from url /?lang=en or /?lang=fr
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeIntercepter = new LocaleChangeInterceptor();
		// define lang as parameter for locale
		localeIntercepter.setParamName("lang");
		return localeIntercepter;
	}
}
