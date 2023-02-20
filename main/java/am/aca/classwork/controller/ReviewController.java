package am.aca.classwork.controller;


import am.aca.classwork.dto.BookResponseDto;
import am.aca.classwork.dto.ReviewCreateRequestDto;
import am.aca.classwork.dto.ReviewResponseDto;
import am.aca.classwork.dto.UserResponseDto;
import am.aca.classwork.service.ReviewService;
import am.aca.classwork.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping
    public ReviewResponseDto createReview(@RequestBody ReviewCreateRequestDto reviewCreateRequestDto){
        return reviewService.createReview(reviewCreateRequestDto);
    }

    @GetMapping(path = {"/{id}"})
    public List<ReviewResponseDto> getUserReviews(@PathVariable("id") Long id){
        return reviewService.getAllReviews(id);
    }

    @GetMapping(path = {"/users/{id}"})
    public List<BookResponseDto> getReviewedBooks(@PathVariable("id") Long id){
        return reviewService.getAllReviewedBooks(id);
    }

    @GetMapping(path = {"/book/{id}"})
    public List<ReviewResponseDto> getReviewsByBookId(@PathVariable("id") Long id){
        return reviewService.getAllReviewsByBookId(id);
    }

}
