package org.maven.FirstMaven;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController {

	@RequestMapping("/welcome")
	public String sayHello() {
		return "Hello";
	}
}
