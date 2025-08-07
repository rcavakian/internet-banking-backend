package br.edu.ifba.internetBanking.config;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class DebugFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        System.out.println("=== DEBUG FILTER ===");
        System.out.println("Method: " + httpRequest.getMethod());
        System.out.println("URI: " + httpRequest.getRequestURI());
        System.out.println("Origin: " + httpRequest.getHeader("Origin"));
        System.out.println("Content-Type: " + httpRequest.getHeader("Content-Type"));
        System.out.println("===================");
        
        chain.doFilter(request, response);
        
        System.out.println("Response Status: " + httpResponse.getStatus());
        System.out.println("=== END DEBUG ===");
    }
}
