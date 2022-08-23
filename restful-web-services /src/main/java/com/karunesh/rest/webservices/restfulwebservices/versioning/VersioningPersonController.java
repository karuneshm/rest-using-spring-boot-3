package com.karunesh.rest.webservices.restfulwebservices.versioning;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	@GetMapping("person/V1")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("person/V2")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Bob", "Charlie")) ;
	}

	
	@GetMapping(path="person", params="version=1")
	public PersonV1 getFirstVersionOfPersonRequestParam() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path ="person", params="version=2")
	public PersonV2 getSecondVersionOfPersonRequestParam() {
		return new PersonV2(new Name("Bob", "Charlie")) ;
	}
	
	@GetMapping(path="person", headers="X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path ="person", headers="X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Bob", "Charlie")) ;
	}
	
	@GetMapping(path="person", produces="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path ="person", produces="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfAcceptHeader() {
		return new PersonV2(new Name("Bob", "Charlie")) ;
	}
	
	
}
