package kr.ac.jejunu;

import javax.servlet.*;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("*********** Filter init *************");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("*********** Filter before *************"); // 사전 처리
        chain.doFilter(request, response); // 서블릿 호출
        System.out.println("************ Filter after **************"); // 사후 처리
    }

    @Override
    public void destroy() {
        System.out.println("*********** Filter destroy ***************");
        Filter.super.destroy();
    }
}
