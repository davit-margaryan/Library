package am.aca.classwork.controller;


import am.aca.classwork.dto.AuthorCreateRequestDto;
import am.aca.classwork.dto.AuthorResponseDto;
import am.aca.classwork.service.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorResponseDto createAuthor(@RequestBody AuthorCreateRequestDto authorCreateRequestDto){
        return authorService.createAuthor(authorCreateRequestDto);
    }
}
