package com.generator.invoice.Utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.generator.invoice.ExceptionHandling.BusinessException;
import com.generator.invoice.ExceptionHandling.ExceptionCodes;
import com.generator.invoice.ExceptionHandling.ExceptionMessages;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JWTUtils {
	
	
	public void validateToken(final String token){
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getSignkey()).build().parseClaimsJws(token);
	}
	public boolean isTokenValid(String token, UserDetails userDetails) throws BusinessException {
	    final String username = extractUsername(token);
	    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) throws BusinessException {
	   return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) throws BusinessException {
	   return extractClaim(token, Claims::getExpiration);
	}
	
	public String extractUsername(String token) throws BusinessException {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws BusinessException {
	    Claims claims = null;
	    try {
	    	claims = extractAllClaims(token);
		}catch(SignatureException ex) {
			throw new BusinessException(ExceptionCodes.USER_INVALID_TOKEN, ExceptionMessages.USER_INVALID_TOKEN);
		}catch(Exception e) {
			throw e;
		}
	    return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSignkey()).build().parseClaimsJws(token).getBody();
	}
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createtoken(claims,userName);
	}
	
	public String createtoken(Map<String, Object> claims, String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignkey(), io.jsonwebtoken.SignatureAlgorithm.HS256).compact();
	}
	
	private Key getSignkey() {
		byte[] keyBytes = Decoders.BASE64.decode(Constants.SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
