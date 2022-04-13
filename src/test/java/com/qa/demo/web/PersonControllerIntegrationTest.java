package com.qa.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.domain.Person;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //sets up the MockMvc object
public class PersonControllerIntegrationTest {

	@Autowired // pull the MockMvc object from the context
	private MockMvc mvc; // class that performs the request (kind of a postman equivalent)
	
	@Autowired
	private ObjectMapper mapper; // java <-> JSON converter that Spring uses
	
	@Test
	void testCreate() throws Exception {
	Person testPerson = new Person(null, "John", 40, 170);
	String testPersonAsJSON = this.mapper.writeValueAsString(testPerson);
	RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testPersonAsJSON);
	
	Person testCreatedPerson = new Person(1, "John", 40, 170);
	String testCreatedPersonAsJSON = this.mapper.writeValueAsString(testCreatedPerson);
	
	ResultMatcher checkStatus = status().isCreated();
	ResultMatcher checkBody = content().json(testCreatedPersonAsJSON);
	
	this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
}
