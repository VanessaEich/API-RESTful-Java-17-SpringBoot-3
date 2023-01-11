package br.com.vanessaeich.services;

import br.com.vanessaeich.exceptions.ResourceNotFoundException;
import br.com.vanessaeich.model.Person;
import br.com.vanessaeich.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Vanessa Eich on 10/01/2023
 */
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){

        logger.info("Log- Finding all people");

        return repository.findAll();
    }

    public Person findById(Long id){

        logger.info("Log- Finding one person");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public Person create(Person person) {
        logger.info("Log- Creating one person");

        return repository.save(person);
    }
    public Person update(Person person) {
        logger.info("Log- Updating one person");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(entity);
    }

    public void delete(Long id) {

        logger.info("Log- Deleting one person");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        repository.delete(entity);
    }

}
