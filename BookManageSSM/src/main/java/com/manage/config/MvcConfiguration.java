package com.manage.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScans({
        @ComponentScan("com.manage.controller")
})
public class MvcConfiguration implements WebMvcConfigurer {

    // 配置 Thymeleaf 视图解析器
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setOrder(1); // 设置解析器的优先级
        resolver.setCharacterEncoding("UTF-8"); // 设置字符编码
        resolver.setTemplateEngine(springTemplateEngine); // 设置模板引擎
        return resolver;
    }

    // 配置 Thymeleaf 模板解析器
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/"); // 设置模板文件的前缀路径
        resolver.setSuffix(".html"); // 设置模板文件的后缀
        resolver.setCharacterEncoding("UTF-8"); // 设置字符编码
        resolver.setTemplateMode(TemplateMode.HTML); // 设置模板模式为 HTML
        resolver.setCacheable(false); // 是否缓存模板（开发环境建议关闭）
        return resolver;
    }

    // 配置 Thymeleaf 模板引擎
    @Bean
    public SpringTemplateEngine springTemplateEngine(ITemplateResolver resolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver); // 设置模板解析器
        return engine;
    }


}
