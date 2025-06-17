package com.dci.ypper.config;

import com.dci.ypper.service.JWTService;
import com.dci.ypper.service.YpprUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization"); // this gets the auth part of the header
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            // this will cut the bearer string in front
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        // this checks if the username exists and isn't authenticated
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // this will get the user object from the userdetailsService
            UserDetails userDetails = context.getBean(YpprUserDetailsService.class).loadUserByUsername(username);

            if(jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //this will pass the information of the request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
