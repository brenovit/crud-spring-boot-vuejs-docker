package io.github.brenovit.store.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;

@Component
public class HeaderHelper {

	@Autowired
	private HttpServletRequest request;
	
	public String getCodigoContratante() {
		return request.getHeader("CodigoContratante");
	}
	
	public String getAuthorization() {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtException("No JWT token found in request headers");
        }

        return header.substring(7);
	}

}
