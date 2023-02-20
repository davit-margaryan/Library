package am.aca.classwork.controller;

import am.aca.classwork.dto.BookCreateRequestDto;
import am.aca.classwork.dto.BookResponseDto;
import am.aca.classwork.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponseDto createBook(@RequestBody BookCreateRequestDto bookCreateRequest){
        return bookService.createBook(bookCreateRequest);
    }

    @GetMapping
    public List<BookResponseDto> getAllBooks(){
        return bookService.getAllBooks();
    }

}
