package one.digitalinovation.cadperson.service;

import one.digitalinovation.cadperson.dto.request.PersonDTO;
import one.digitalinovation.cadperson.dto.response.MessageResponseDTO;
import one.digitalinovation.cadperson.entity.Person;
import one.digitalinovation.cadperson.mapper.PersonMapper;
import one.digitalinovation.cadperson.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
//                .firstName(personDTO.getFirstName())     **A criação do personToSave foi substituido pelo metodo PersonMapper
//                .lastName(personDTO.getLastName())
//                .birthDate(personDTO.getBirthDate())
//                .phones(personDTO.getPhones())
//                .build();

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
