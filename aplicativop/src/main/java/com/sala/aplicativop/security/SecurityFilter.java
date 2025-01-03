package com.sala.aplicativop.security;

import com.sala.aplicativop.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try {
           String requestPath = request.getRequestURI();

           if (requestPath.equals("/usuarios/login") || requestPath.equals("/usuarios/register")) {
               filterChain.doFilter(request, response);
               return;
           }

           var token = this.recoverToken(request);
           if(token == null){
               throw new RuntimeException("Token não fornecido ou ausente");
           }
           var login = tokenService.validateToken(token);
           UserDetails user = userRepository.findByLogin(login);

           var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
           SecurityContextHolder.getContext().setAuthentication(authentication);
       } catch (RuntimeException e) {
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.setContentType("application/json");
           response.getWriter().write("{\"message\": \"" + e.getMessage() + "\"}");
           return;
       }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
