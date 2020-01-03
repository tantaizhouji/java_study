package com.yangqihang.filter;

import com.yangqihang.entity.Account;
import com.yangqihang.entity.Permission;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@WebFilter(urlPatterns = "/*")
public class AccountFilter implements Filter {

    //忽略uri组，公共uri
    private final String[] IGNORE_URI = {
            "/css/", "/js/", "/images/", "/errorPage", "/index", "/default.jpg","/favicon.ico",
            "/account/login", "/api/v1/manager/account/accountLogin",
            "/account/register", "/api/v1/manager/account/accountRegister", "/account/logout"};

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

        //获取访问页面的uri
        String uri = httpRequest.getRequestURI();
        System.out.println("uri: " + uri);
        //检查uri是否是忽略uri组里面
        Boolean exist = checkURI(uri);

        if (exist) {
            //在，访问公共uri
            chain.doFilter(httpRequest, httpResponse);

            return;
        }

        //获取会话中登录的账号
        Account account = (Account) httpRequest.getSession().getAttribute("account");

        //判断账号是否为空
        if (null == account) {
            //为空,未登录，跳转登录界面
            httpResponse.sendRedirect("/account/login");
            return;
        }

        //判断登录账号的权限是否为空
        if (null == account.getPerList()) {
            request.setAttribute("msg", "该账号为设置权限，请向管理员申请权限" + uri);
            request.getRequestDispatcher("/errorPage").forward(request, response);

            return;
        }

        //判断访问为的uri是否在账号的权限里
        if (!hasAuth(account.getPerList(), uri)) {
            request.setAttribute("msg", "您无权限访问当前页面" + uri);
            request.getRequestDispatcher("/errorPage").forward(request, response);

            return;
        }

        //通过访问
        chain.doFilter(request, response);
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

    /**
     * 判断访问的uri是否在登录的用户权限中
     *
     * @param perList
     * @param uri
     * @return
     */
    private boolean hasAuth(List<Permission> perList, String uri) {
        for (Permission permission : perList) {
            if (uri.startsWith(permission.getUri())) {
                return true;
            }
        }

        return false;
    }
}
