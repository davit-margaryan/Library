package am.aca.classwork.controller;

import am.aca.classwork.dto.BookResponseDto;
import am.aca.classwork.dto.ReviewResponseDto;
import am.aca.classwork.dto.UserCreateRequestDto;
import am.aca.classwork.dto.UserResponseDto;
import am.aca.classwork.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserCreateRequestDto userCreateRequestDto){
        return userService.createUser(userCreateRequestDto);
    }

    @GetMapping(path = {"/{id}"})
    public UserResponseDto getOneUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

}
