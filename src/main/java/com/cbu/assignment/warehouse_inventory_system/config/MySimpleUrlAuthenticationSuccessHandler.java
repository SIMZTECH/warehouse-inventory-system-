package com.cbu.assignment.warehouse_inventory_system.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

     public MySimpleUrlAuthenticationSuccessHandler() {
        super();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
          handle(request, response, authentication);
        clearAuthenticationAttributes(request);
        // throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
    }
    
    protected void handle(
        HttpServletRequest request,
        HttpServletResponse response, 
        Authentication authentication
) throws IOException {

    String targetUrl = determineTargetUrl(authentication);

    if (response.isCommitted()) {
        logger.debug(
                "Response has already been committed. Unable to redirect to "
                        + targetUrl);
        return;
    }

    logger.info(response);
    logger.info(targetUrl);
    redirectStrategy.sendRedirect(request, response, targetUrl);
}

protected String determineTargetUrl(final Authentication authentication) {

    logger.info("HELLO FAVOUR");

    
    Map<String, String> roleTargetUrlMap = new HashMap<>();
    // roleTargetUrlMap.put("ROLE_USER", "/homepage");
    roleTargetUrlMap.put("ROLE_ADMIN", "/admin/");
    roleTargetUrlMap.put("ROLE_RECEIVER", "/receivingClerk/");
    // roleTargetUrlMap.put("ROLE_SHIPPER", "/receivingClerk");
    roleTargetUrlMap.put("ROLE_FULFILLER", "/OrderFulfilment/");
    roleTargetUrlMap.put("ROLE_STOCK", "/stockingClerk/");
    roleTargetUrlMap.put("ROLE_CUSTOMER", "/customer/");

    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    
    for (final GrantedAuthority grantedAuthority : authorities) {
        String authorityName = grantedAuthority.getAuthority();
     
        if(roleTargetUrlMap.containsKey(authorityName)) {
            logger.info(authorityName.toString());
            return roleTargetUrlMap.get(authorityName);
        }
    }

    throw new IllegalStateException();
}

protected void clearAuthenticationAttributes(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
        return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
}
}
