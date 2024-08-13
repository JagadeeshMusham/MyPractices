package com.musham.InterviewQuestions.amphora.engineering_test.src.main.java.com.amphora.engineering_test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amphora.engineering_test.dao.Person;
import com.amphora.engineering_test.service.FamilyService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class implements all the controllers which are required to implements
 * Family service
 * 
 * @author Admin
 * 
 */
@RestController
public class FamilyController {

	/**
	 * This object holds the familyService class which have actual service
	 * implementation
	 */
	FamilyService familyService;

	/**
	 * The constructor
	 */
	public FamilyController() {
		familyService = new FamilyService();
	}

	/**
	 * This controller will be used to handle multiple add operations to the family
	 * 
	 * @param input
	 *            Input buffer in String format
	 * 
	 * @return returns the string formatted result of the each person add operation
	 */
	@PostMapping("/persons")
	public String addPersons(@RequestBody String input) {
		System.out.println("The input: " + input);
		ArrayList<Person> personsList = null;

		StringBuilder result = new StringBuilder();

		try {
			ObjectMapper mapper = new ObjectMapper();

			// taking the input into persons list
			personsList = mapper.readValue(input,
					mapper.getTypeFactory().constructCollectionType(List.class, Person.class));
			System.out.println(personsList);
		} catch (IOException e) {
			e.printStackTrace();
			return "Error! Received invalid request, Please check the request input and try again";
		}

		// handling add operation of individual person
		personsList.forEach(person -> {
			try {
				result.append(familyService.addPersonToFamily(person)).append("\n");
			} catch (Exception e) {
				result.append(e.toString()).append("\n");
			}
		});
		// for (Person person : personsList) {
		// try {
		// result.append(familyService.addPersonToFamily(person)).append("\n");
		// } catch (Exception e) {
		// result.append(e.toString()).append("\n");
		// }
		// }

		System.out.println(result);
		return result.toString();
	}

	/**
	 * This controller will be used to handle single add operation to the family
	 * 
	 * @param input
	 *            Input buffer in String format
	 * 
	 * @return returns the string formatted result of the add operation
	 */
	@PostMapping("/person")
	public String addPerson(@RequestBody String input) {
		System.out.println("The output: " + input);

		try {
			ObjectMapper mapper = new ObjectMapper();
			Person person = mapper.readValue(input, Person.class);
			System.out.println(person);

			return familyService.addPersonToFamily(person);
		} catch (IOException e) {
			e.printStackTrace();
			return "Error! Received invalid request, Please check the request input and try again";
		}
	}

	/**
	 * This controller will be used to list all added family members
	 * 
	 * @return returns the string formatted list of family members
	 */
	@GetMapping("/persons")
	public String GetPersons() {
		return familyService.getPersons();
	}

	/**
	 * This controller will be used to list all added family members in ascending
	 * order by age
	 * 
	 * @return returns the string formatted list of family members in ascending
	 *         order by age
	 */
	@GetMapping("/persons/ascending")
	public String GetPersonsByAscendingAge() {
		return familyService.getPersonsByAscendingAge();
	}

	/**
	 * This controller will be used to list all added family members in descending
	 * order by age
	 * 
	 * @return returns the string formatted list of family members in descending
	 *         order by age
	 */
	@GetMapping("/persons/descending")
	public String GetPersonsByDescendingAge() {
		return familyService.getPersonsByDescendingAge();
	}

	/**
	 * This controller will be used to list all added family members in pretty
	 * format by person's generation
	 * 
	 * @return returns the string formatted list of family members in pretty order
	 *         by their generation
	 * 
	 */
	@GetMapping("/persons/pretty")
	public String GetPersonsByPretty() {
		return familyService.getFamilyByPretty();
	}

	/**
	 * This controller will be used to get the parents information of a person
	 * 
	 * @param personName
	 *            The person name to display hios parents
	 *            
	 * @return return the all parents and grant parents of the person
	 */
	@GetMapping("/parents/{personName}")
	public String GetParentsOfPerson(@PathVariable("personName") String personName) {
		return familyService.getParents(personName);
	}
}