package com.janita.poi.config;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Janita on 2017/6/13 0013- 下午 5:34
 * 该类是：
 */
public class CharacterFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("\n****** ");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
