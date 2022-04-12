package com.qa.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.demo.domain.Person;

@Service // stores the main business logic of the application
public class PersonService {

	// LIST - we haven't got a DB yet so we need to store date somewhere
	private List<Person> peeps = new ArrayList<>();

	
//CRUD	
	public Person createPerson(Person p) {
		this.peeps.add(p);
		Person created = this.peeps.get(this.peeps.size() - 1);
		return created;
	}

	public List<Person> getAllPeeps() {
		return this.peeps;
	}

	public Person getPerson(Integer id) {
		return this.peeps.get(id);
	}

	public Person replacePerson(Integer id, Person newPerson) {
		Person body = this.peeps.set(id, newPerson);
		return body;
	}

	public void removePerson(@PathVariable Integer id) {
		this.peeps.remove(id.intValue());

	}
}
