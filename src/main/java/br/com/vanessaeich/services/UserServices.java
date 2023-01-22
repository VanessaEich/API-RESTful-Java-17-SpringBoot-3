package br.com.vanessaeich.services;

import br.com.vanessaeich.controllers.PersonController;
import br.com.vanessaeich.dtos.v1.PersonDTO;
import br.com.vanessaeich.dtos.v2.PersonDTOV2;
import br.com.vanessaeich.exceptions.RequiredObjectIsNullException;
import br.com.vanessaeich.exceptions.ResourceNotFoundException;
import br.com.vanessaeich.mapper.DozerMapper;
import br.com.vanessaeich.mapper.PersonMapperV2;
import br.com.vanessaeich.model.Person;
import br.com.vanessaeich.repositories.PersonRepository;
import br.com.vanessaeich.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vanessa Eich on 10/01/2023
 */
@Service
public class UserServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name " + username + "!");
        var user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }
}
