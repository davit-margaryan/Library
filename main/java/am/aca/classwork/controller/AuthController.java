package am.aca.classwork.controller;


import am.aca.classwork.dto.AuthRequestDto;
import am.aca.classwork.dto.AuthResponseDto;
import am.aca.classwork.dto.RegRequestDto;
import am.aca.classwork.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto authRequestDto){
        return authService.login(authRequestDto);
    }

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegRequestDto requestDto){
        return authService.register(requestDto);
    }
}
