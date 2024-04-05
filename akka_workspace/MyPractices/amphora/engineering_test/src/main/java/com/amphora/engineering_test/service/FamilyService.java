package com.amphora.engineering_test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.amphora.engineering_test.dao.Person;

/**
 * This class will have all implementations of all actions which are implemented
 * in this service
 * 
 * @author Admin
 *
 */
@Service
public class FamilyService {

	/**
	 * This will be used to hold the added persons in the map
	 */
	private Map<String, Person> personsMap = new HashMap<>();

	/**
	 * This will be used to hold the great grand parents at max we can have two
	 * great grand parents
	 */
	private List<Person> greatGrandParents = new ArrayList<>();

	/**
	 * This method add the given person to family member
	 * 
	 * @param person
	 *            The person to whom we have to add to the family, if we specify
	 *            parents in the person object then that parent has to be added to
	 *            the family first
	 * 
	 * @return returns the status of the add operation
	 */
	public String addPersonToFamily(Person person) {
		StringBuilder sbResult = new StringBuilder();
		int generation = 0;

		// verify whether this person already added to the family?
		if (personsMap.containsKey(person.getName())) {
			sbResult.append("Error! The person with name '").append(person.getName())
					.append("' is already inserted please try with other name of the person");
			return sbResult.toString();
		}

		// get parent and verify if they are exceeding two which indicates invalid data
		List<String> parents = person.getParents();
		if (parents.size() > 2) {
			sbResult.append("Error! The person with name '").append(person.getName()).append("' and age '")
					.append(person.getAge())
					.append(" is unable to add as invalid number of parents found in the input data");
			return sbResult.toString();
		}

		// if parents are not available means the adding person is great grand parents
		// which we can have at max of two.
		if (parents.size() == 0) {
			if (greatGrandParents.size() < 2) {
				greatGrandParents.add(person);
			} else {
				sbResult.append("Error! The person with name '").append(person.getName()).append("' and age '")
						.append(person.getAge())
						.append(" is unable to add as this person not having valid parent details and two great grand parents already filled");
				return sbResult.toString();
			}
		} else {
			for (String parentName : parents) {
				if (personsMap.containsKey(parentName) == false) {
					// if any of one parent is not added to the family then it's an invalid person
					// to the family
					sbResult.append("Error! The person with name '").append(person.getName()).append("' and age '")
							.append(person.getAge()).append(" is unable to add as parent '").append(parentName)
							.append("' not found in the available list of persons. Please add parents and then try to add kids later");
					return sbResult.toString();
				}
			}
		}

		for (String parentName : parents) {
			Person parent = personsMap.get(parentName);
			parent.getKids().add(person.getName());

			// increase the generation only when parent generation less than equals to
			// current generation
			if (generation <= parent.getGeneration()) {
				generation = parent.getGeneration() + 1;
			}
		}

		person.setGeneration(generation);
		personsMap.put(person.getName(), person);

		sbResult.append("The person with name '").append(person.getName()).append("' and age '").append(person.getAge())
				.append("' is added succesfully");
		return sbResult.toString();
	}

	/**
	 * @return returns the list of persons in the String format which are available
	 *         in the family
	 */
	public String getPersons() {
		StringBuilder result = new StringBuilder();

		for (Person person : personsMap.values()) {
			result.append(person.toString()).append("\n");
		}

		return result.toString();
	}

	/**
	 * convert the hash map to tree map based on the person's age which is useful to
	 * display the ascending or descending order of the family members based on the
	 * person's age
	 * 
	 * @return returns the tree map
	 */
	private TreeMap<String, Person> getTreeMap() {
		TreeMap<String, Person> treeMap = new TreeMap<>();
		for (Person person : personsMap.values()) {
			StringBuilder key = new StringBuilder();
			if (person.getAge() < 10) {
				key.append('0');
			}
			key.append(person.getAge()).append('|').append(person.getName());
			treeMap.put(key.toString(), person);
		}

		return treeMap;
	}

	/**
	 * @return returns the string formatted list of persons in ascending order based
	 *         on the person's age
	 */
	public String getPersonsByAscendingAge() {
		TreeMap<String, Person> treeMap = getTreeMap();
		treeMap.descendingMap().values().toString();

		return treeMap.values().toString();
	}

	/**
	 * @return returns the string formatted list of persons in descending order
	 *         based on the person's age
	 */
	public String getPersonsByDescendingAge() {
		TreeMap<String, Person> treeMap = getTreeMap();
		return treeMap.descendingMap().values().toString();
	}

	/**
	 * it returns the max generation value of the entire family, great grand parents
	 * generation will be considers as 1, grand parents as 2 and so on
	 * 
	 * @return returns the max generation value
	 */
	private int getMaxGeneration() {
		int maxLevel = -1;
		for (Person person : personsMap.values()) {
			if (maxLevel < person.getGeneration())
				maxLevel = person.getGeneration();
		}

		return maxLevel;
	}

	/**
	 * This is used to list all added family members in pretty format by person's
	 * generation i.e., it will give kind of triangle shape of the family members
	 * 
	 * @return returns the string formatted list of family members in pretty order
	 *         by their generation
	 */
	public String getFamilyByPretty() {

		// it will hold all the persons in generation level
		Queue<Person> personsQueue = new LinkedList<>();

		// Make all persons of the family as not visited
		personsMap.forEach((key, person) -> person.setVisisted(false));
		greatGrandParents.forEach(person -> personsQueue.add(person));

		StringBuilder result = new StringBuilder();
		Person person = null;
		int currentGeneration = -1;
		int maxGeneration = getMaxGeneration();

		while (true) {
			// Retrieves and deletes head of queue
			person = personsQueue.poll();
			if (person == null) {
				break;
			}

			person.getKids().forEach(child -> {
				Person childPerson = personsMap.get(child);
				if (childPerson != null && !childPerson.isVisisted()) {
					// mark the person as visited add that person to the queue to process further
					childPerson.setVisisted(true);
					personsQueue.add(childPerson);
				}
			});

			if (currentGeneration != person.getGeneration()) {
				currentGeneration = person.getGeneration();
				// after each generation display new line
				result.append("\n");

				// display appropriate tab space to get proper pyramid shape
				for (int counter = currentGeneration; counter < maxGeneration; counter++) {
					result.append("\t");
				}
			}

			result.append(person.getName()).append("\t");
		}

		return result.toString();
	}

	/**
	 * This will be used to get the parents information of a person
	 * 
	 * @param personName
	 *            The person name to display hios parents
	 * 
	 * @return return the all parents and grant parents of the person
	 */
	public String getParents(String personName) {
		Person person = personsMap.get(personName);

		StringBuilder sbResult = new StringBuilder();
		if (person == null) {
			sbResult.append("Error! The person with name '").append(personName).append(
					"' not found in the available list of persons. Please try again with appropriate person name");
			return sbResult.toString();
		}

		// Make all persons of the family as not visited
		personsMap.forEach((key, p) -> p.setVisisted(false));

		// it will hold all the parents of the person
		Queue<String> parentsQueue = new LinkedList<>(person.getParents());

		sbResult.append("The parents and grand parents of person '").append(personName).append("' are: ");
		while (!parentsQueue.isEmpty()) {
			// Retrieves and deletes head of queue
			String parentName = parentsQueue.poll();

			Person parent = personsMap.get(parentName);
			if (parent.isVisisted()) {
				continue; // as this parent already visited
			}

			// Mark the parent node as visited
			parent.setVisisted(true);
			parentsQueue.addAll(parent.getParents());

			// displaying the parent name to output
			sbResult.append(parent.getName()).append(",\t");
		}
		
		return sbResult.toString();
	}
}
