package br.com.vanessaeich.repositories;

import br.com.vanessaeich.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vanessa Eich on 11/01/2023
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
