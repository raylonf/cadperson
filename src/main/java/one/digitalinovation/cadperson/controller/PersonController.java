package one.digitalinovation.cadperson.controller;

import one.digitalinovation.cadperson.dto.MessageResponseDTO;
import one.digitalinovation.cadperson.entity.Person;
import one.digitalinovation.cadperson.repository.PersonRepository;
import one.digitalinovation.cadperson.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);

    }

}
