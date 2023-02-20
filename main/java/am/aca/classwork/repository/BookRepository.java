package am.aca.classwork.repository;

import am.aca.classwork.entity.book.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface BookRepository extends Repository<Book,Long> {
    Book findById(Long id);

    List<Book> findAll();

    Book save(Book book);

    @Query("select b from book b where lower( b.author.name)  like lower(concat('%',:authorName,'%'))")
    List<Book> getAllByAuthorNameLike(@Param("authorName")String authorName);
}
