package com.qa.demo.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.domain.Person;
import com.qa.demo.service.PersonService;

@RestController // tells Spring this is a controller
// Rest compliant  (representational state transfer)
public class PersonController {

	private PersonService service;

	@Autowired //tells Spring to fetch the PersonService from the context - dependency injection
	public PersonController(PersonService service) {
		super();
		this.service=service;
	}

// CRUD functionality
// ResponseEntity is an extension of HttpEntity that represents an HTTP response including status code, headers and body
	
// create
	@PostMapping("/create") // 201 - created
	public ResponseEntity<Person> createPerson(@RequestBody Person p) {
		Person created = this.service.createPerson(p);
		ResponseEntity<Person> response = new ResponseEntity<Person>(created, HttpStatus.CREATED);
		return response;
	}

// read all
	@GetMapping("/getAll") // 200 - OK
	public ResponseEntity<List<Person>> getAllPeeps() {
		return ResponseEntity.ok(this.service.getAllPeeps());

	}

// read one 
	@GetMapping("/get/{id}") // 200 - OK
	public Person getPerson(@PathVariable Integer id) {
		return this.service.getPerson(id);
	}

// update
	@PutMapping("/replace/{id}") // 202 - accepted
	public ResponseEntity<Person> replacePerson(@PathVariable Integer id, @RequestBody Person newPerson) {
		Person body = this.service.replacePerson(id, newPerson);
		ResponseEntity<Person> response = new ResponseEntity<Person>(body, HttpStatus.ACCEPTED);
		return response;
	}

// delete
	@DeleteMapping("/remove/{id}") // 204 - no content
	public ResponseEntity<?> removePerson(@PathVariable Integer id) {
		this.service.removePerson(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// read by name
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Person>> getPersonByName(@PathVariable String name) {
		List<Person> found = this.service.getPeepsByName(name);
		return ResponseEntity.ok(found);
	}
	// read by age
	@GetMapping("/getByAge/{age}")
	public ResponseEntity<List<Person>> getPersonByAge(@PathVariable Integer age) {
		List<Person> found = this.service.getPeepsByAge(age);
		return ResponseEntity.ok(found);
	}
}
