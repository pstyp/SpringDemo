package com.qa.demo.web;

import java.util.ArrayList;
import java.util.List;

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

@RestController // tells Spring this is a controller
// Rest compliant  (representational state transfer)
public class PersonController {

// LIST - we haven't got a DB yet so we need to store date somewhere
	private List<Person> peeps = new ArrayList<>();

// CRUD functionality
// ResponseEntity is an extension of HttpEntity that represents an HTTP response including status code, headers and body
	
// create
	@PostMapping("/create") // 201 - created
	public ResponseEntity<Person> createPerson(@RequestBody Person p) {
		this.peeps.add(p);
		Person created = this.peeps.get(this.peeps.size() - 1);
		ResponseEntity<Person> response = new ResponseEntity<Person>(created, HttpStatus.CREATED);
		return response;
	}

// read all
	@GetMapping("/getAll") // 200 - OK
	public ResponseEntity<List<Person>> getAllPeeps() {
		return ResponseEntity.ok(this.peeps);

	}

// read one 
	@GetMapping("/get/{id}") // 200 - OK
	public Person getPerson(@PathVariable Integer id) {
		return this.peeps.get(id);
	}

// update
	@PutMapping("/replace/{id}") // 202 - accepted
	public ResponseEntity<Person> replacePerson(@PathVariable Integer id, @RequestBody Person newPerson) {
		Person body = this.peeps.set(id, newPerson);
		ResponseEntity<Person> response = new ResponseEntity<Person>(body, HttpStatus.ACCEPTED);
		return response;
	}

// delete
	@DeleteMapping("/remove/{id}") // 204 - no content
	public ResponseEntity<?> removePerson(@PathVariable Integer id) {
		this.peeps.remove(id.intValue());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
