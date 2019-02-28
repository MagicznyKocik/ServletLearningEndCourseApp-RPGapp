package pl.adamLupinski.ServletLearninng.RPGApp.filter;


import pl.adamLupinski.ServletLearninng.RPGApp.dao.UserDAO;
import pl.adamLupinski.ServletLearninng.RPGApp.model.User;
import pl.adamLupinski.ServletLearninng.RPGApp.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (httpServletRequest.getUserPrincipal() != null &&
                httpServletRequest.getSession().getAttribute("user") == null){
            saveUserInSession(httpServletRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void saveUserInSession(HttpServletRequest request){
        UserService userService = new UserService();
        String username = request.getUserPrincipal().getName();
        User userByUsername = userService.getUserByUsername(username);
        request.getSession(true).setAttribute("user", userByUsername);
    }

    @Override
    public void destroy() {

    }
}
