package com.janita.poi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Janita on 2017/6/13 0013- 下午 4:12
 * 该类是：
 */
@Configuration
public class ResourcesConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/excel/**").setCachePeriod(1).addResourceLocations("classpath:/excel/");
        super.addResourceHandlers(registry);
    }
}
