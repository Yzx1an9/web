package com.yzx.filter;

import com.yzx.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class Transacitonfilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request,response);
            JdbcUtils.CommitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.RollbackAndClose();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
