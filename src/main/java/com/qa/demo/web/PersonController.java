package com.qa.demo.web;

import java.util.ArrayList;
import java.util.List;

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

// create
	@PostMapping("/create")
	public Person createPerson(@RequestBody Person p) {
		this.peeps.add(p);
		Person created = this.peeps.get(this.peeps.size() - 1);
		return created;
	}

// read all
	@GetMapping("/getAll")
	public List<Person> getAllPeeps() {
		return this.peeps;

	}

// read one 
	@GetMapping("/get/{id}")
	public Person getPerson(@PathVariable Integer id) {
		return this.peeps.get(id);
	}

// update
	@PutMapping("/replace/{id}")
	public Person replacePerson(@PathVariable Integer id, @RequestBody Person newPerson) {
		Person body = this.peeps.set(id, newPerson);
		return body;
	}

// delete
	@DeleteMapping("/remove/{id}")
	public void removePerson(@PathVariable Integer id) {
		this.peeps.remove(id.intValue());
	}
}
