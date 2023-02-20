package am.aca.classwork.service;


import am.aca.classwork.builder.ReviewResponseBuilder;
import am.aca.classwork.dto.ReviewResponseDto;
import am.aca.classwork.dto.UserCreateRequestDto;
import am.aca.classwork.dto.UserResponseDto;
import am.aca.classwork.entity.book.Review;
import am.aca.classwork.entity.book.User;
import am.aca.classwork.repository.ReviewRepository;
import am.aca.classwork.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public UserService(UserRepository userRepository,
                       ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public UserResponseDto createUser(UserCreateRequestDto userCreateRequestDto){
        User user = new User();
        user.setUsername(userCreateRequestDto.getUsername());
        user.setReviews(new ArrayList<>());
        User savedUser = userRepository.save(user);
        return buildUserResponseDto(savedUser);

    }

    private static UserResponseDto buildUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        return userResponseDto;
    }

    public UserResponseDto getUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        return buildUserResponseDto(user.orElseThrow(()-> new RuntimeException("There is no user with that id")));
    }
}
