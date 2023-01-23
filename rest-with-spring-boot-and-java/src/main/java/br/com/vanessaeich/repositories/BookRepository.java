package br.com.vanessaeich.repositories;

import br.com.vanessaeich.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vanessa Eich on 11/01/2023
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
