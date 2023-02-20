package am.aca.classwork.service;

import am.aca.classwork.builder.BookResponseBuilder;
import am.aca.classwork.dto.BookCreateRequestDto;
import am.aca.classwork.dto.BookResponseDto;
import am.aca.classwork.entity.book.Author;
import am.aca.classwork.entity.book.Book;
import am.aca.classwork.entity.book.Review;
import am.aca.classwork.repository.AuthorRepository;
import am.aca.classwork.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public BookResponseDto createBook(BookCreateRequestDto requestDto) {
        Optional<Author> author = authorRepository.findById(requestDto.getAuthorId());
        if (author.isEmpty()) {
            throw new RuntimeException("There is no existing author with that id");
        }
        Book book = new Book();
        book.setName(requestDto.getBookName());
        book.setAuthor(author.get());
        book.setReviews(new ArrayList<>());
        Book savedBook = bookRepository.save(book);
        return BookResponseBuilder.buildBookResponseDto(savedBook);
    }

    public List<BookResponseDto> getAllBooks(){
        return bookRepository.findAll().stream().map(BookResponseBuilder::buildBookResponseDto).collect(Collectors.toList());
    }

}
