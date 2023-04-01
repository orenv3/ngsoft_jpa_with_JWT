package com.demo.ngsoft.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationProvider authenticationProvider;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
            final String authHeader = request.getHeader("Authorization");
            final String jwtToken;
            final String userEmail;

            if(authHeader == null || !(authHeader.startsWith("Bearer "))) {
                filterChain.doFilter(request, response);
                return;
            }
        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(jwtToken);

          if(userEmail !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = // checking userName via DB
                    this.userDetailsService.loadUserByUsername(userEmail);



            if(jwtService.isTokenValid(jwtToken,userDetails)){
            //update SecurityContext
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        }else if(userEmail !=null){
            UserDetails userDetails = // check userName via DB
                    this.userDetailsService.loadUserByUsername(userEmail);
            if(!(userDetails.isEnabled())){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                filterChain.doFilter(request, response);
                return;
            }

        }
        filterChain.doFilter(request,response);
    }
}
