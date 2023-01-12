package br.com.vanessaeich.controllers;

import br.com.vanessaeich.dtos.v1.PersonDTO;
import br.com.vanessaeich.dtos.v2.PersonDTOV2;
import br.com.vanessaeich.services.PersonServices;
import br.com.vanessaeich.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vanessa Eich on 10/01/2023
 */
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(produces = { MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<PersonDTO> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return services.findById(id);
    }
    @PostMapping(produces = { MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = { MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonDTO create(@RequestBody PersonDTO person) {
        return services.create(person);
    }

    @PostMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = { MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {
        return services.createV2(person);
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = { MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonDTO update(@RequestBody PersonDTO person) {
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value= "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
