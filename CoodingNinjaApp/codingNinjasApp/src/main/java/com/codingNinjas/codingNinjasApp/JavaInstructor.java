package com.codingNinjas.codingNinjasApp;

import org.springframework.stereotype.Component;

@Component("javaInstructor")
public class JavaInstructor implements Instructor {

	String name;
	String age;
	
	@Override
	public void setInstructorDetails(String name, String age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String takeClass() {
		return "Hi my name is "+this.name+" and I will be your java"
				+ "instructor";
	}
	
	public void init() {
		System.out.println("Java instructor bean created");
	}
	
	public void cleanup() {
		System.out.println("Java instructor bean about to be destroyed");
	}

}
