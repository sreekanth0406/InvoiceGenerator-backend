package com.generator.invoice.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.generator.invoice.ExceptionHandling.BusinessException;
import com.generator.invoice.ExceptionHandling.ExceptionCodes;
import com.generator.invoice.ExceptionHandling.ExceptionMessages;
import com.generator.invoice.Utils.JWTUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final HandlerExceptionResolver resolver;
	
	@Autowired
	private JWTUtils jwtService;
	
	private final UserDetailsService userDetailsService;

    JwtAuthenticationFilter(UserDetailsService userDetailsService, @Qualifier("handlerExceptionResolver")HandlerExceptionResolver resolver) {
        this.userDetailsService = userDetailsService;
        this.resolver = resolver;
    }
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException,RuntimeException {
		// TODO Auto-generated method stub
		try {
			final String authHeader = request.getHeader("Authorization");
		    final String jwt;
		    final String userEmail;
		    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
		      filterChain.doFilter(request, response);
		      return;
		    }
		    jwt = authHeader.substring(7);
		    userEmail = jwtService.extractUsername(jwt);
		    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		      UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
		      
		      if (jwtService.isTokenValid(jwt,userDetails)) {
		        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
		            userDetails,
		            null,
		            userDetails.getAuthorities()
		        );
		        authToken.setDetails(
		            new WebAuthenticationDetailsSource().buildDetails(request)
		        );
		        SecurityContextHolder.getContext().setAuthentication(authToken);
		      }else {
		    	  resolver.resolveException(request, response, null, new BusinessException(ExceptionCodes.USER_INVALID_TOKEN,ExceptionMessages.USER_INVALID_TOKEN));
		      }
		    }
		    filterChain.doFilter(request, response);
		}catch(BusinessException be) {

	    	  resolver.resolveException(request, response, null, be);
		}
		catch(Exception ex) {
			resolver.resolveException(request, response, null, ex);
		}
		
		
	}

}
