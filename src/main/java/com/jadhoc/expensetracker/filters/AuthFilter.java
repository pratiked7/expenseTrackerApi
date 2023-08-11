package com.jadhoc.expensetracker.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jadhoc.expensetracker.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader != null){

            String[] authHeaderArr = authHeader.split("Bearer ");

            if(authHeaderArr.length > 1 && authHeaderArr[1] != null){

                String token = authHeaderArr[1];

                DecodedJWT decodedJWT;
                try {
                    Algorithm algorithm = Algorithm.HMAC256(Constants.API_SECRET_KEY);
                    JWTVerifier jwtVerifier = JWT.require(algorithm)
                            .withIssuer("auth0")
                            .build();
                    decodedJWT = jwtVerifier.verify(token);

                    httpServletRequest.setAttribute("userId",
                            Integer.parseInt(decodedJWT.getClaims().get("userId").toString()));

                } catch (Exception e){
                    httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid or expired token");
                    return;
                }
            } else {
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer <token>");
                return;
            }
        } else {
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
