package com.karunesh.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource source;
	
	@GetMapping("/hello-world")
	public String hellWorld( ) {
		return "Hello World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean hellWorldBean( ) {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping("/hello-world/path-varaible/{name}")
	public HelloWorldBean hellWorldPathVariable(@PathVariable(value="name") String name ) {
		return new HelloWorldBean("Hello World, " + name);
	}
	
	@GetMapping("/hello-world/query-varaible")
	public HelloWorldBean hellWorldQueryVariable(@RequestParam String name ) {
		return new HelloWorldBean("Hello World, " + name);
	}
	
	@GetMapping("/hello-world/internationalized")
	public String hellWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return source.getMessage("good.morning.message", null, "Default Good Morning", locale);
	}
	
	

}
