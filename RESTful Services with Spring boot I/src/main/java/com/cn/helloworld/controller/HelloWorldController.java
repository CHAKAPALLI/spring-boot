package com.cn.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class HelloWorldController {
	
	@RequestMapping("/hello")
	public String hello()
	{
		return "Hello Coding Ninjas !";
	}

	@GetMapping("/hello-world")
	public String helloWorld()
	{
		return "Hello from Earth !";
	}
	
	@GetMapping("/hello-mars/{name}")
	public String helloFromMar(@PathVariable String name) {
		return "hello from Mars";
	}

}
