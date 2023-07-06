package com.cops.ntsf.filter;

import com.cops.ntsf.util.JwtUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    /**
     * Default constructor.
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain)
            throws IOException, ServletException {

        String authHeader = ((HttpServletRequest) req).getHeader("Authorization");
        String reqPath = ((HttpServletRequest) req).getRequestURI();
        String[] validPathsForAuthFilter = {"/"};

        JwtUtils jwtUtils = new JwtUtils(authHeader);
        for (String path : validPathsForAuthFilter) {
            if (reqPath.startsWith(path))
                if (jwtUtils.verifyJwtAuthentication()) {
                    chain.doFilter(req, resp);
                } else {
                    ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
        }

    }
}