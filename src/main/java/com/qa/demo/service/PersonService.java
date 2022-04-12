package com.qa.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.demo.domain.Person;
import com.qa.demo.repo.PersonRepo;

@Service // stores the main business logic of the application
public class PersonService {

	private PersonRepo repo;
	
	@Autowired
	public PersonService(PersonRepo repo) {
		super();
		this.repo=repo;
	}

//CRUD	
	// INSERT INTO Person;
	public Person createPerson(Person p) {
		Person created = this.repo.save(p);
		return created;
	}
   // SELECT * FROM Person;
	public List<Person> getAllPeeps() {
		return this.repo.findAll();
	}
    // SELECT * FROM Person WHERE ID =
	public Person getPerson(Integer id) {
		Optional<Person> found = this.repo.findById(id);
		return found.get();
	}

	// UPDATE
	public Person replacePerson(Integer id, Person newPerson) {
		Person existing = this.repo.findById(id).get();
		existing.setAge(newPerson.getAge());
		existing.setHeight(newPerson.getHeight());
		existing.setName(newPerson.getName());
		Person updated = this.repo.save(existing);
		return updated;
	}

	// DELETE FROM PERSON WHERE ID =
	public void removePerson(@PathVariable Integer id) {
		this.repo.deleteById(id);

	}
}
