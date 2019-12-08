package io.github.brenovit.store.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class SwaggerController {
	
	@GetMapping(path="")
	public RedirectView redirectApiDocSwagger() {
		return new RedirectView("swagger-ui.html");
	}	
}
