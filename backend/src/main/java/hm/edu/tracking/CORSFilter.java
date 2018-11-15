//======================================================================================================================
// Copyright (c) 2018 BMW Group. All rights reserved.
//======================================================================================================================
package hm.edu.tracking;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet Filter implementation class CORSFilter.
 *
 * Cross-origin resource sharing (CORS) is a mechanism that allows
 * JavaScript on a web page to make AJAX requests to another domain, different from the domain from where it originated.
 * By default, such web requests are forbidden in browsers, and they will result into same origin security policy
 * errors. Using java CORS filter, you may allow the webpage to make requests from other domains as well (known as cross
 * domain requests).
 */
public class CORSFilter implements Filter {

    /**
     * Default empty constructor.
     */
    public CORSFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // nothing to do    
    }

    @Override
    public void destroy() {
        // nothing to do
    }

    /**
     * Access-Control-Allow-Origin: specifies the authorized domains to make cross-domain request. Use “*” as value if
     * there is no restrictions.
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        ((HttpServletResponse) response).addHeader(
                "Access-Control-Allow-Origin", "*");
        chain.doFilter(request, response);
    }

}
