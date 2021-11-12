package one.digitalinovation.cadperson.service;

import one.digitalinovation.cadperson.dto.request.PersonDTO;
import one.digitalinovation.cadperson.dto.response.MessageResponseDTO;
import one.digitalinovation.cadperson.entity.Person;
import one.digitalinovation.cadperson.exception.PersonNotFoundException;
import one.digitalinovation.cadperson.mapper.PersonMapper;
import one.digitalinovation.cadperson.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private  PersonRepository personRepository;

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
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyExists(id);
//        Optional<Person> optionalPerson = personRepository.findById(id);   #Foi substituido pelo metodo lambda#
//        if (optionalPerson.isEmpty()) {
//            throw new PersonNotFoundException(id);
//        }
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyExists(id);
        personRepository.deleteById(id);
    }


    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID");

    }

    private Person verifyExists(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return null;
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
