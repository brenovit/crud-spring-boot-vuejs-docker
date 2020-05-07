package io.github.brenovit.store.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HeaderHelper {

	@Autowired
	private HttpServletRequest request;
		
	public String getAuthorization() {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring(7);
	}

}
