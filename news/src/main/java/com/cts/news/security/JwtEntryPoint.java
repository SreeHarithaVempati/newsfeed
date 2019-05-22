package com.cts.news.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.cts.news.security.JwtEntryPoint;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

	public static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		logger.info("commence() execution has started!");
		logger.error("error message->{}", ex);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
		logger.info("commence()execution has completed!");

	}

}
