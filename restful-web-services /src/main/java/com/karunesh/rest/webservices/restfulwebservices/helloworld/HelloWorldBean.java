package com.karunesh.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
	
	private String message;
	
	public HelloWorldBean() {
		
	}

	public HelloWorldBean(String message) {
		this.message = message;
	}

	public String getmessage() {
		return message;
	}

	public void setmessage(String string) {
		this.message = string;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
	
	

}
