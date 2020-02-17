package io.github.brenovit.store.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.JwtException;

@Component
public class HeaderHelper {

	@Autowired
	private HttpServletRequest request;
		
	public String getAuthorization() {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            throw new JwtException("No JWT token found in request headers");
        }

        return header.substring(7);
	}

}
