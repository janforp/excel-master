package com.janita.poi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

/**
 * Created by Janita on 2017/6/13 0013- 下午 5:36
 * 该类是：
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(characterFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

    @Bean
    public Filter characterFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("iso8859-1");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }
}
