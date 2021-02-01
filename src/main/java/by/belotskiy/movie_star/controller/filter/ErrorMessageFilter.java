package by.belotskiy.movie_star.controller.filter;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ErrorMessageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String errorMessage = (String)session.getAttribute(SessionAttributeName.ERROR_MESSAGE);
        if(errorMessage != null){
            request.setAttribute(RequestParameterName.ERROR_MESSAGE,errorMessage);
        }
        session.removeAttribute(SessionAttributeName.ERROR_MESSAGE);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
