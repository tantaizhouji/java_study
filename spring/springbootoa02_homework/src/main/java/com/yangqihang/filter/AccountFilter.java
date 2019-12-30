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

    //所有权限都可以访问的uri
    private final String[] IGNORE_URI = {"/css/", "/js/", "/images/", "/index", "/account/login", "/account/accountLogin", "/account/register", "/account/accountRegister"};

    /**
     * 加载Filter
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Filter执行器
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //HttpServlet的功能更多,强制转换
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
     * URI检查器
     *
     * @param uri
     * @return
     */
    private Boolean checkURI(String uri) {
        for (String currURI : IGNORE_URI) {
            if (uri.startsWith(currURI)) {
                return true;
            }
        }

        return false;
    }
}
