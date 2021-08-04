package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exceptions.DifferentIdsException;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MessageResponseDTO create(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person createdPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message(String.format("Created person named %s %s with ID %d.",
                        createdPerson.getFirstName(),
                        createdPerson.getLastName(),
                        createdPerson.getId()))
                .build();
    }

    public List<PersonDTO> findAll() {
        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyById(id);

        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyById(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException, DifferentIdsException {
        verifyById(id);

        if(!id.equals(personDTO.getId())){
            throw new DifferentIdsException(id, personDTO.getId());
        }

        Person newPerson = personRepository.save(personMapper.toModel(personDTO));

        return MessageResponseDTO
                .builder()
                .message(String.format("Updated person named %s %s with ID %d.",
                        newPerson.getFirstName(),
                        newPerson.getLastName(),
                        newPerson.getId()))
                .build();
    }

    private Person verifyById(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
