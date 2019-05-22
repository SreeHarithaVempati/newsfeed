package com.cts.news.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	private static Logger logger = LoggerFactory.getLogger(JwtGenerator.class);
	@Value("${practice.key}")
	private String secretKey;
	@Value("${practice.expirationTime}")
	private long expirationTime;

	public String generateToken(Authentication authentication) {
		logger.info("generateToken() execution has started ");
		User user = (User) authentication.getPrincipal();
		String token = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expirationTime * 365))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
		logger.info("generateToken() execution has completed ");
		return token;
	}

	public boolean validateToken(String token) {
		logger.error("validateToken() execution has started ");
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			logger.info("validateToken() execution has completed ");
			return true;
		} catch (Exception ex) {
			
			logger.error("Jwt token validation error->{} ", ex.getMessage());
			logger.info("validateToken() execution has completed ");
			return false;
		}
	}

	public String userNameFromToken(String token) {
		logger.info("userNameFromToken() execution has started ");
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

	}

}
