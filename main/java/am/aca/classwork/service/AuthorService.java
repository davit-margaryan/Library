package am.aca.classwork.service;

import am.aca.classwork.builder.BookResponseBuilder;
import am.aca.classwork.dto.AuthorCreateRequestDto;
import am.aca.classwork.dto.AuthorResponseDto;
import am.aca.classwork.dto.BookResponseDto;
import am.aca.classwork.entity.book.Author;
import am.aca.classwork.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class AuthorService {
    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponseDto createAuthor(AuthorCreateRequestDto authorCreateRequestDto){
        Author author = new Author();
        author.setName(authorCreateRequestDto.getAuthorName());
        author.setBooks(new ArrayList<>());
        Author savedAuthor = authorRepository.save(author);
        return buildAuthorResponse(savedAuthor);
    }

    public AuthorResponseDto getAuthor(Long authorId){
        Optional<Author> author = authorRepository.findById(authorId);
        return buildAuthorResponse(author.orElseThrow(()->new RuntimeException(" There is no id ")));
    }

    private AuthorResponseDto buildAuthorResponse(Author author){
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setAuthorName(author.getName());
        authorResponseDto.setId(author.getId());
        authorResponseDto.setBooks(author.getBooks().stream().map(BookResponseBuilder::buildBookResponseDto).collect(Collectors.toList()));
        return authorResponseDto;
    }
}
