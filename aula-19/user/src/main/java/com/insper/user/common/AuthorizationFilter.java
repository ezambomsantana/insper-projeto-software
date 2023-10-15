package com.insper.user.common;

import com.insper.user.user.LoginService;
import com.insper.user.user.dto.ReturnUserDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@Order(2)
public class AuthorizationFilter implements Filter {

    Map<String, List<String>> postAuth = Map.of(
            "/user", Arrays.asList("ADMIN")
    );

    @Autowired
    private LoginService loginService;

    List<String> openRoutes = Arrays.asList("/user");

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String uri = req.getRequestURI();
        String method = req.getMethod();
        String token = req.getHeader("token");

        if (method.equals("POST") && postAuth.containsKey(uri)) {
            List<String> roles = postAuth.get(uri);

            ReturnUserDTO user = loginService.get(token);

            boolean found = false;
            for (String role : user.getRoles()) {
                if (roles.contains(role)) {
                    found = true;
                }
            }
            if (found) {
                chain.doFilter(request, response);
            } else {
                throw new RuntimeException();
            }
        }
        chain.doFilter(request, response);
    }

}