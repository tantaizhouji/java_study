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

    private final String[] IGNORE_URI = {"/css/", "/js/", "/images/", "/index", "/account/login", "/account/accountLogin", "/account/register", "/account/accountRegister"};

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

        Object account = httpRequest.getSession().getAttribute("account");

        //判断账号是否登录,没登录
        if (null == account) {

            //获取访问页面的uri
            String uri = httpRequest.getRequestURI();
            //检查uri是否通行
            Boolean exist = checkURI(uri);

            if (exist) {
                //通过，访问页面
                chain.doFilter(httpRequest, httpResponse);
                return;
            }

            //没通过，跳转登录界面
            httpResponse.sendRedirect("/account/login");
            return;
        }

        //账号登录
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
