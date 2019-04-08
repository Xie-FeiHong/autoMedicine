package cn.nexuslink.fileter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 罗浩 on 2017/12/17.
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName="corsFilter")
public class CorsFileter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String origin = (String) servletRequest.getRemoteHost()+":"+servletRequest.getRemotePort();
        response.setHeader("Access-Control-Allow-Origin", "*"); // 跨域问题
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE,PATCH"); // 允许的请求方式
        response.setHeader("Access-Control-Max-Age", "3600"); // 预检的有效期
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE"); // 预检的时候，允许通过的请求头
        response.setHeader("Access-Control-Allow-Credentials","true"); // 允许Ajax带cookie
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
