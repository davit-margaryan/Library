package am.aca.classwork.service;

import am.aca.classwork.builder.BookResponseBuilder;
import am.aca.classwork.builder.ReviewResponseBuilder;
import am.aca.classwork.dto.BookResponseDto;
import am.aca.classwork.dto.ReviewCreateRequestDto;
import am.aca.classwork.dto.ReviewResponseDto;
import am.aca.classwork.entity.book.Book;
import am.aca.classwork.entity.book.Review;
import am.aca.classwork.entity.book.User;
import am.aca.classwork.repository.BookRepository;
import am.aca.classwork.repository.ReviewRepository;
import am.aca.classwork.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static am.aca.classwork.builder.ReviewResponseBuilder.buildReviewResponseDto;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    public ReviewResponseDto createReview(ReviewCreateRequestDto reviewCreateRequestDto) {
        Review review = new Review();
        review.setBook(bookRepository.findById(reviewCreateRequestDto.getBookId()));
        review.setUser(userRepository.getReferenceById(reviewCreateRequestDto.getUserId()));
        review.setRate(reviewCreateRequestDto.getRate());
        review.setComment(reviewCreateRequestDto.getComment());
        reviewRepository.save(review);
        return buildReviewResponseDto(reviewRepository.save(review));
    }

    public List<ReviewResponseDto> getAllReviews(Long id){
        ArrayList<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();
        User referenceById = userRepository.getReferenceById(id);
        List<Review> reviews = referenceById.getReviews();
        for (Review review : reviews) {
            ReviewResponseDto reviewResponseDto = ReviewResponseBuilder.buildReviewResponseDto(review);
            reviewResponseDtos.add(reviewResponseDto);
        }
        return reviewResponseDtos;
    }

    public List<BookResponseDto> getAllReviewedBooks(Long id){
        ArrayList<BookResponseDto> bookResponseDtos = new ArrayList<>();
        User referenceById = userRepository.getReferenceById(id);
        List<Review> reviews = referenceById.getReviews();
        for (Review review : reviews) {
            Book book = review.getBook();
            BookResponseDto bookResponseDto = BookResponseBuilder.buildBookResponseDto(book);
            bookResponseDtos.add(bookResponseDto);
        }
        return bookResponseDtos;
    }

    public List<ReviewResponseDto> getAllReviewsByBookId(Long id){
        ArrayList<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();
        Book book = bookRepository.findById(id);
        List<Review> reviews = book.getReviews();
        for (Review review : reviews) {
            reviewResponseDtos.add(ReviewResponseBuilder.buildReviewResponseDto(review));
        }
        return reviewResponseDtos;
    }
}
