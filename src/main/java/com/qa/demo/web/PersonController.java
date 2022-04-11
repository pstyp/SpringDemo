package com.qa.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // tells Spring this is a controller
// Rest compliant  (representational state transfer)
public class PersonController {

	@GetMapping("/helloWorld") // this is an endpoint
	public String hello() {
		return "Hello world!";
	}
}
