package com.yangqihang.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*")
public class AccountFilter implements Filter {

    private final String[] IGNORE_URI = {"/css/", "/js/", "/images/", "/index", "/account/login", "/account/logout", "/account/checkAccount"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //显示Filter是否加载
        System.out.println("====Filter init====");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //HttpServletRequest,HttpServletRequest的功能更多
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //获取当前访问的URI
        String uri = httpRequest.getRequestURI();
        //判断URI是否存在忽略组里面
        Boolean exist = checkURI(uri);

        if (exist) {
            //通过操作
            chain.doFilter(httpRequest, httpResponse);
            return;
        }

        //获取Session里面登录的account用户
        Object account = httpRequest.getSession().getAttribute("account");
        //判断Session里的用户是否为空
        if (null == account) {
            //跳转到登录页面
            httpResponse.sendRedirect("/account/login");
            return;
        }

        //放行操作
        chain.doFilter(httpRequest, httpResponse);
    }

    /**
     * 判断URI是否在忽略里面
     *
     * @param uri
     * @return
     */
    private Boolean checkURI(String uri) {

        //遍历忽略数组
        for (String currURI : IGNORE_URI) {

            //判断uri的开头是否在忽略数组里面
            if (uri.startsWith(currURI)) {
                return true;
            }
        }

        return false;
    }
}
