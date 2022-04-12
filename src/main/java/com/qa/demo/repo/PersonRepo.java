package com.qa.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

	
	//SPRING WILL AUTO-GENERATE ALL OF THE BASIC CRUD FUNCTIONALITY :)
}
