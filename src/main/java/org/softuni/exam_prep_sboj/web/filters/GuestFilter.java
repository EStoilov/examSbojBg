package org.softuni.exam_prep_sboj.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/home", "/add-job"
})
public class GuestFilter implements  Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        
        if(request.getSession().getAttribute("username") == null){
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
