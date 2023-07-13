package com.app.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Person;
import com.app.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonController {
	private final PersonRepository personRepository;

	public PersonController() {
		this.personRepository = null;
		System.out.println("in the constr of" + getClass());

	}

	@Autowired
	public PersonController(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@GetMapping
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> persons = personRepository.findAll();
		return ResponseEntity.ok(persons);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
		return personRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		// Calculate the BMI before saving
		double bmi = person.calculateBMI();
		person.setBmi(bmi);

		Person savedPerson = personRepository.save(person);
		return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
	}
      @PostMapping("/bmi")
      public double calculateBMI(@RequestBody Person person) {
          double heightInMeters = person.getHeight() / 100; // Convert height to meters
          double bmi = person.getWeight() / (heightInMeters * heightInMeters); // Calculate BMI
          person.setBmi(bmi); // Set the calculated BMI in the Person object
          return bmi;}
      
      
//	@PutMapping("/{id}")
//	public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
//		return personRepository.findById(id).map(person -> {
//			// Update the properties
//			person.setHeight(updatedPerson.getHeight());
//			person.setWeight(updatedPerson.getWeight());
//			// Recalculate the BMI
//			double bmi = person.calculateBMI();
//			person.setBmi(bmi);
//
//			Person savedPerson = personRepository.save(person);
//			return ResponseEntity.ok(savedPerson);
//		}).orElse(ResponseEntity.notFound().build());
//	}
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
//        return personRepository.findById(id)
//                .map(person -> {
//                    personRepository.delete(person);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
}
