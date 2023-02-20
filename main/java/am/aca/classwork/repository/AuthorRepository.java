package am.aca.classwork.repository;

import am.aca.classwork.entity.book.Author;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface AuthorRepository extends Repository<Author,Long> {
    Optional<Author> findById(Long id);

    Optional<Author> findByName(String authorName);
    Author save(Author author);

}
