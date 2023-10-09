package com.insper.user.common;

import com.insper.user.user.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class LoginFilter implements Filter {

    @Autowired
    private UserService userService;

    List<String> openRoutes = Arrays.asList("/user");

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String email = req.getHeader("email");
        String password = req.getHeader("password");

        String uri = req.getRequestURI();
        String method = req.getMethod();

        if (method.equals("GET") && openRoutes.contains(uri)) {
            chain.doFilter(request, response);
        } else {
            userService.validateUser(email, password);

            chain.doFilter(request, response);
        }

    }

}