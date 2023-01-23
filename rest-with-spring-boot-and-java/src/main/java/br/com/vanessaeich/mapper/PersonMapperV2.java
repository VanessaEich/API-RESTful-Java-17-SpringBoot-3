package br.com.vanessaeich.mapper;

import br.com.vanessaeich.dtos.v2.PersonDTOV2;
import br.com.vanessaeich.model.Person;
import br.com.vanessaeich.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Vanessa Eich on 11/01/2023
 */
@Service
public class PersonMapperV2 {

    public PersonDTOV2 convertEntityToDto (Person person) {
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setBirhtDate(new Date());
        dto.setGender(person.getGender());

        return dto;
    }

    public Person converDtoToEntity (PersonDTOV2 personDTOV2) {
        Person entity = new Person();
        entity.setId(personDTOV2.getId());
        entity.setFirstName(personDTOV2.getFirstName());
        entity.setLastName(personDTOV2.getLastName());
        entity.setAddress(personDTOV2.getAddress());
        //dto.setBirhtDate(new Date());
        entity.setGender(personDTOV2.getGender());

        return entity;
    }
}
