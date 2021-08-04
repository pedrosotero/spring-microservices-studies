package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.exceptions.DifferentIdsException;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@Valid @RequestBody PersonDTO personDTO) {
        return personService.create(personDTO);
    }

    @GetMapping
    public List<PersonDTO> getAllPeople() {
        return personService.findAll();
    }

    @GetMapping("/{personId}")
    public PersonDTO getPersonById(@PathVariable("personId") Long personId) throws PersonNotFoundException {
        return personService.findById(personId);
    }

    @PutMapping("/{personId}")
    public MessageResponseDTO updateById(@PathVariable("personId") Long personId,
                                         @Valid @RequestBody PersonDTO personDTO) throws PersonNotFoundException, DifferentIdsException {
        return personService.updateById(personId, personDTO );
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonById(@PathVariable("personId") Long personId) throws PersonNotFoundException {
        personService.deleteById(personId);
    }
}
