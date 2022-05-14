package com.pro100user.autoservicebackend.security.jwt;

import com.pro100user.autoservicebackend.security.UserSecurity;
import com.pro100user.autoservicebackend.security.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final UserSecurityService userSecurityService;

    @Value("${security.jwt.header}")
    private String Authorization;

    @Value("${security.jwt.prefix}")
    private String Prefix;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            String email = jwtProvider.getLoginFromToken(token);
            UserSecurity userSecurity = userSecurityService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userSecurity, null, userSecurity.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(Authorization);
        if (hasText(bearer) && bearer.startsWith(Prefix + " ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
