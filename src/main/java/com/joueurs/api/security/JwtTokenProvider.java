package com.joueurs.api.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.joueurs.api.exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")	
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")	
	private int jwtExpirationInMs;
	
	// generate Token
	
	public String generateToken (Authentication authentication) {
		
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expirateDate = new Date(currentDate.getTime() + jwtExpirationInMs);
		
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expirateDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		return token;		
	}
	
	// get username from the token
	
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();		
	}
	
	// validate JWT token
	
	public boolean validationToken(String token) {
		
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch(SignatureException exception) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid JWT signature");		
		}catch(MalformedJwtException exception) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid JWT Token");
		}catch(UnsupportedJwtException exception) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Unsupported JWT token");
		}catch(ExpiredJwtException exception) {
			throw new BlogAPIException (HttpStatus.BAD_REQUEST,"Expired JWT  token");					
		}catch (IllegalArgumentException exception){
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
			
		}
		
	}
	
}
